package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.dialogue.*;
import io.github.spiritstead.main.*;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Mayor implements NPC {
    private final StateHandler stateHandler;
    private final Sprites sprites;
    private final Mover mover;
    private final SolidArea solidArea;
    private final FrameGate frameGate;
    private final TileCollision tileCollision;
    private final Collision collision;
    private final WorldPosition worldPosition = new WorldPosition();
    private final FrameGate walkingFrameGate;
    private final Animation axeAnimation, talkingAnimation;
    private final Direction.Holder direction;
    private final Map<Integer, String> allDialogue;

    private int screenX, screenY;
    private Node node;
    private Node nodeTempLeft;
    private Node nodeTempRight;
    private Sprite sprite;
    private int spriteNum = 1;
    private boolean collisionOn = false;
    private int speed;

    public Mayor(Sprites sprites, Map<Integer, String> allDialogue, SolidArea solidArea, int speed) {
        this.sprites = sprites;
        this.allDialogue = allDialogue;
        this.solidArea = solidArea;
        this.mover = new Mover(this);
        this.frameGate = new FrameGate(120);
        this.tileCollision = new TileCollision(Game.tileM, this);
        this.collision = new Collision();
        this.direction = new Direction.Holder(Direction.LEFT);

        this.speed = speed;
        this.walkingFrameGate = new FrameGate(30);
        this.stateHandler = new StateHandler(NpcState.MOVING);

        this.axeAnimation = Animation.looping(new FrameGate(30), new ArrayList<>(Arrays.asList(
                sprites.getNextSprite(Direction.DOWN, 1),
                sprites.getNextSprite(Direction.DOWN, 2)
        )));
        this.talkingAnimation = Animation.looping(new FrameGate(30), new ArrayList<>(Arrays.asList(
                sprites.getNextSprite(Direction.RIGHT, 1)
        )));
    }

    public void setAction() {
        if (this.frameGate.tick()) {
            this.direction.set(Direction.UP);
            this.frameGate.reset();
        }

    }

    public void update() {
        setAction();
        if (this.stateHandler.isState(NpcState.MOVING)) {
            updateMovePlayerAnimation();
            checkCollisions();
            this.mover.move();
        }

    }

    @Override
    public void setDialogueNode(Node node) { this.node = node; }

    public void interact() {
        this.stateHandler.setCurrentState(NpcState.CONVERSING);

        if (this.node == null) {
            Game.ui.setUi(Game.ui.gameUIScreen);
            Game.screens.setScreen(Game.screens.gameScreen);
            this.stateHandler.setCurrentState(NpcState.AXING);
            this.nodeTempRight = null;
            this.nodeTempLeft = null;
        } else if (this.node.isPhase(DialoguePhase.ADVANCING)) {
            Game.ui.dialogueUI.text.setTextString(this.node.getText());
            this.node = this.node.nextLeft();
        } else if (this.node.isPhase(DialoguePhase.CHOOSING)) {
            Game.ui.playerDialogueUIScreen.dialogueUIText1.setTextString(this.node.getText());
            Game.ui.playerDialogueUIScreen.dialogueUIText2.setTextString(this.node.getPrev().getRight().getText());
            Game.ui.uiScreen = Game.ui.playerDialogueUIScreen;
            this.node = this.node.nextLeft();
        } else if (this.node.isPhase(DialoguePhase.CHOOSINGRESPONSE)) {
            if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 0) {
                Game.ui.dialogueUI.text.setTextString(this.node.getText());
                this.node.triggerEvent();
                Game.ui.uiScreen = Game.ui.dialogueUI;
            } else if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 1) {
                Game.ui.dialogueUI.text.setTextString(this.node.getPrev().getPrev().getRight().getLeft().getText());
                this.node.getPrev().getPrev().getRight().getLeft().triggerEvent();
                Game.ui.uiScreen = Game.ui.dialogueUI;
            }
            this.nodeTempLeft = Node.builder().addEvents(this.node.getDialogueEvents()).build();
            this.nodeTempRight = Node.builder().addEvents(this.node.getPrev().getPrev().getRight().getLeft().getDialogueEvents()).build();
            this.node = this.node.nextLeft();
        }

    }

    public void updateMovePlayerAnimation() {
        if (walkingFrameGate.tick()) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.walkingFrameGate.reset();
        }

    }

    private void checkCollisions() {
        this.collisionOn = false;
        this.collisionOn = tileCollision.check();
        checkPlayerCollision();
        checkObjectCollision();

    }

    private void checkPlayerCollision() {
        if (collision.check(this, Game.player)) {
            this.collisionOn = true;
        }
    }

    private void checkObjectCollision() {
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) != null && collision.check(this, Game.aSetter.obj.get(i))) {
                this.collision.check(this, Game.aSetter.obj.get(i));
            }
        }
    }

    public void draw() {
        initialiazeScreenPositionRelativeToPlayer();
        if (entityIsWithinScreenBounds()) {
            if (stateHandler.isState(NpcState.MOVING)) {
                updateSprite();
                Game.batch.draw(sprite, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
            } else if (stateHandler.isState(NpcState.CONVERSING)) {
                talkingAnimation.update();
                Game.batch.draw(talkingAnimation.getCurrentSprite(), screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
                if (this.nodeTempLeft != null) {
                    if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 0) {
                        this.nodeTempLeft.drawEvent();
                    } else if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 1) {
                        this.nodeTempRight.drawEvent();
                    }
                }
            } else if (stateHandler.isState(NpcState.AXING)) {
                axeAnimation.update();
                Game.batch.draw(axeAnimation.getCurrentSprite(), screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
            }

        }
    }

    private void updateSprite() {
        sprite = null;
        sprite = sprites.getNextSprite(this.direction.get(), spriteNum);
    }

    private boolean entityIsWithinScreenBounds() {
        return getWorldPosition().getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.getScreenPosition().getX() &&
                getWorldPosition().getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.getScreenPosition().getX() &&
                getWorldPosition().getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.getScreenPosition().getY() &&
                getWorldPosition().getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.getScreenPosition().getY();
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        this.screenX = getWorldPosition().getX() - Game.player.getWorldPosition().getX() + Game.player.getScreenPosition().getX();
        this.screenY = getWorldPosition().getY() - Game.player.getWorldPosition().getY() + Game.player.getScreenPosition().getY();
    }

    @Override
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public Direction getDirection() { return this.direction.get(); }
    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public boolean isCollisionOn() { return this.collisionOn; }
    @Override
    public int getSpeed() { return this.speed; }

}
