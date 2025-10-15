package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.dialogueTree.*;
import io.github.spiritstead.main.*;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mayor implements NPC {
    private StateHandler stateHandler;
    private Sprite sprite;
    Sprites sprites;
    private Mover mover;
    public int spriteNum = 1;
    private SolidArea solidArea;
    public boolean collisionOn = false;
    private int speed;
    private Direction direction;
    GamePanel gp;
    private FrameGate frameGate;
    private TileCollisionType tileCollision;
    private Collision collision;
    public WorldPosition worldPosition = new WorldPosition();
    public Map<Integer, String> allDialogue = new HashMap<>();
    private int screenX, screenY;
    public Node node;
    private FrameGate walkingFrameGate;
    private Animation axeAnimation, talkingAnimation;
    private Node nodeTempLeft;
    private Node nodeTempRight;

    public Mayor(GamePanel gp) {
        this.gp = gp;
        this.mover = new Mover(this);
        this.sprites = new Sprites();
        this.frameGate = new FrameGate(120);
        this.tileCollision = new TileCollisionType(Game.tileM, this);
        this.collision = new Collision();

        this.speed = 1;
        this.solidArea = new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        this.sprites.load();
        this.direction = Direction.LEFT;
        this.allDialogue = Game.script.mayorDialogue;
        this.walkingFrameGate = new FrameGate(30);
        this.stateHandler = new StateHandler(NpcState.AXING);

        this.axeAnimation = new Animation(new FrameGate(30), new ArrayList<>(Arrays.asList(
                sprites.down1,
                sprites.down2
        )));
        this.talkingAnimation = new Animation(new FrameGate(30), new ArrayList<>(Arrays.asList(
                sprites.right1
        )));
    }

    public void setAction() {
        if (this.frameGate.tick()) {
            this.direction = Direction.UP;
            this.frameGate.reset();
        }

    }

    public void update() {
//        setAction();
        if (this.stateHandler.isState(NpcState.MOVING)) {
            updateMovePlayerAnimation();
            checkCollisions();
            this.mover.move();
        }

    }

    @Override
    public void setDialogueNode(Node node) {
        this.node = node;
    }

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
//                    new Node("", null, this.node.getDialogueEvents(), null, null);
            this.nodeTempRight = Node.builder().addEvents(this.node.getPrev().getPrev().getRight().getLeft().getDialogueEvents()).build();
//                    new Node("", null, this.node.getPrev().getPrev().getRight().getLeft().getDialogueEvents(), null, null);
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
        tileCollision.check();
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
                talkingAnimation.draw(screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
                if (this.nodeTempLeft != null) {
                    if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 0) {
                        this.nodeTempLeft.drawEvent();
                    } else if (Game.ui.playerDialogueUIScreen.optionCursor.optionNum == 1) {
                        this.nodeTempRight.drawEvent();
                    }
                }
            } else if (stateHandler.isState(NpcState.AXING)) {
                axeAnimation.draw(screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

            }

        }
    }

    private void updateSprite() {
        sprite = null;
        switch (direction) {
            case UP:
                sprite = sprites.frames.get(Direction.UP)[spriteNum - 1];
                break;
            case DOWN:
                sprite = sprites.frames.get(Direction.DOWN)[spriteNum - 1];

                break;
            case LEFT:
                sprite = sprites.frames.get(Direction.LEFT)[spriteNum - 1];
                break;
            case RIGHT:
                sprite = sprites.frames.get(Direction.RIGHT)[spriteNum - 1];
                break;
            default:
                sprite = sprites.down1;
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return getWorldPosition().getX() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getX() - Game.player.screenPosition.getX() &&
                getWorldPosition().getX() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX() &&
                getWorldPosition().getY() + ScreenSetting.TILE_SIZE > Game.player.getWorldPosition().getY() - Game.player.screenPosition.getY() &&
                getWorldPosition().getY() - ScreenSetting.TILE_SIZE < Game.player.getWorldPosition().getX() + Game.player.screenPosition.getY();
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        this.screenX = getWorldPosition().getX() - Game.player.getWorldPosition().getX() + Game.player.screenPosition.getX();
        this.screenY = getWorldPosition().getY() - Game.player.getWorldPosition().getY() + Game.player.screenPosition.getY();
    }

    @Override
    public void setCollisionOn(boolean collisionOn) { this.collisionOn = collisionOn; }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public Direction getDirection() { return this.direction; }
    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public boolean isCollisionOn() { return this.collisionOn; }
    @Override
    public int getSpeed() { return this.speed; }

}
