package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.dialogue.*;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.GameObjects;
import io.github.spiritstead.tools.FrameGate;

import java.io.File;
import java.util.Map;

public final class Mayor implements NPC {
    private final State state;
    private final Sprites sprites;
    private final Mover mover;
    private final SolidArea solidArea;
    private final TileCollision tileCollision;
    private final Collision collision;
    private final WorldPosition worldPosition;
    private final FrameGate walkingFrameGate;
    private final Animation axeAnimation, talkingAnimation;
    private final Direction.Holder direction;
    private final Map<Integer, String> allDialogue;
    private final Action moveUp, moveDown;

    private Action action;
    private int screenX, screenY;
    private Node node;
    private Node nodeTempLeft;
    private Node nodeTempRight;
    private Sprite sprite;
    private int spriteNum = 1;
    private boolean collisionOn = false;
    private int speed;

    public Mayor(Sprites sprites, Map<Integer, String> allDialogue, SolidArea solidArea, int speed, Collision collision,
                 Direction.Holder direction, State state, Animation axeAnimation, Animation talkingAnimation,
                 Action moveUp, TileCollision tileCollision, Mover mover, WorldPosition worldPosition, Action moveDown) {
        this.sprites = sprites;
        this.allDialogue = allDialogue;
        this.solidArea = solidArea;
        this.collision = collision;
        this.direction = direction;
        this.speed = speed;
        this.state = state;
        this.axeAnimation = axeAnimation;
        this.talkingAnimation = talkingAnimation;
        this.moveUp = moveUp;
        this.tileCollision = tileCollision;
        this.mover = mover;
        this.worldPosition = worldPosition;
        this.walkingFrameGate = new FrameGate(30);
        this.moveDown = moveDown;
        this.action = moveUp;

    }

    public void update() {
        this.direction.set(action.update());
        switch ((NpcState) this.state.getCurrent()) {
            case MOVING:
                this.updateMovePlayerAnimation();
                this.checkCollisions();
                this.mover.move(this);
                break;
            case AXING:
                axeAnimation.update();
                break;
            case CONVERSING:
                talkingAnimation.update();
                break;
        }
    }

    @Override
    public void setDialogueNode(Node node) { this.node = node; }

    public void interact() {
        this.state.setCurrent(NpcState.CONVERSING);

        if (this.node == null) {
            Game.ui.setUi(Game.ui.gameUIScreen);
            Game.screens.setScreen(Game.screens.gameScreen);
            this.state.setCurrent(NpcState.AXING);
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
        sprite = null;
        sprite = sprites.getNextSprite(this.direction.get(), spriteNum);

    }

    private void checkCollisions() {
        this.collisionOn = false;
        this.collisionOn = tileCollision.check(Game.tileM, this);
        this.checkPlayerCollision();
        this.checkObjectCollision();

    }

    private void checkPlayerCollision() {
        if (collision.check(this, Game.player)) {
            this.collisionOn = true;
        }
    }

    private void checkObjectCollision() {
        for (int i = 0; i < Game.aSetter.gameObjects.size(); i++) {
            if (Game.aSetter.gameObjects.get(i) != null && collision.check(this, Game.aSetter.gameObjects.get(i))) {
                this.collisionOn = true;
            }
        }
    }

    public void draw() {
        this.initialiazeScreenPositionRelativeToPlayer();
        if (entityIsWithinScreenBounds()) {
            if (this.state.is(NpcState.MOVING)) {
                Game.batch.draw(this.sprite, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
            } else if (this.state.is(NpcState.CONVERSING)) {
                Game.batch.draw(this.talkingAnimation.getCurrentSprite(), screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
                if (this.nodeTempLeft != null) {
                    if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 0) {
                        this.nodeTempLeft.drawEvent();
                    } else if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 1) {
                        this.nodeTempRight.drawEvent();
                    }
                }
            } else if (state.is(NpcState.AXING)) {
                Game.batch.draw(axeAnimation.getCurrentSprite(), screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
            }

        }
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
    public void onEventBus(EventType eventType) {
        this.action = this.moveDown;
        this.state.setCurrent(NpcState.MOVING);
    }

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
