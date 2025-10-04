package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.*;
import io.github.spiritstead.dialogue.*;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.Axe;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mayor implements NPC {
    private StateHandler stateHandler;
    private Dialogue dialogue;
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
    public DialogueNode dialogueNode;
    private FrameGate walkingFrameGate;
    private Animation axeAnimation, talkingAnimation;
    private Conversation conversation;

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
        this.dialogue = new Dialogue();

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

    public void interact() {
        this.stateHandler.setCurrentState(NpcState.CONVERSING);

        if (Game.player.inventory.contains(Axe.class)) {

            this.dialogueNode = new DialogueNode("you have the axe", null, Dialogue.Phase.STARTING);

            if (this.dialogue.phase == Dialogue.Phase.STARTING) {
                Game.ui.dialogueUI.text.currentDialogue = this.dialogueNode.text;
                this.dialogue.phase = Dialogue.Phase.ENDING;
            } else if (this.dialogue.phase == Dialogue.Phase.ENDING) {
                Game.ui.uiScreen = Game.ui.gameScreenUI;
                Game.screens.setScreen(Game.screens.gameScreen);
                this.stateHandler.setCurrentState(NpcState.AXING);
                this.dialogue.phase = Dialogue.Phase.STARTING;
            }

        } else {
            //initialize dialogue
            this.dialogueNode = new DialogueNode(Game.script.mayorDialogue.get(0));
            this.dialogueNode.left = new DialogueNode(Game.script.mayorDialogue.get(1));
            this.dialogueNode.right = new DialogueNode(Game.script.mayorDialogue.get(2));
            this.dialogueNode.left.left = new DialogueNode(Game.script.mayorDialogue.get(3), new ArrayList<>(Arrays.asList(
                new NoBenifit(),
                new SpawnAxe()
            )));
            this.dialogueNode.right.left = new DialogueNode(Game.script.mayorDialogue.get(4), new ArrayList<>(Arrays.asList(
                new IncreaseXP(),
                new SpawnAxe()
            )));

            if (this.dialogue.phase == Dialogue.Phase.STARTING) {
                Game.ui.dialogueUI.text.currentDialogue = this.dialogueNode.text;
                this.dialogue.phase = Dialogue.Phase.ADVANCING;
            } else if (this.dialogue.phase == Dialogue.Phase.ADVANCING) {
                Game.ui.playerDialogueUI.dialogueUIText1.currentDialogue = this.dialogueNode.left.text;
                Game.ui.playerDialogueUI.dialogueUIText2.currentDialogue = this.dialogueNode.right.text;
                this.dialogue.phase = Dialogue.Phase.CHOOSING;
                Game.ui.uiScreen = Game.ui.playerDialogueUI;
            } else if (this.dialogue.phase == Dialogue.Phase.CHOOSING) {
                if (Game.ui.playerDialogueUI.optionCursor.optionNum == 0) {
                    Game.ui.dialogueUI.text.currentDialogue = this.dialogueNode.left.left.text;
                    this.dialogueNode.left.left.triggerEvent();
                    Game.ui.uiScreen = Game.ui.dialogueUI;
                } else if (Game.ui.playerDialogueUI.optionCursor.optionNum == 1) {
                    Game.ui.dialogueUI.text.currentDialogue = this.dialogueNode.right.left.text;
                    this.dialogueNode.right.left.triggerEvent();
                    Game.ui.uiScreen = Game.ui.dialogueUI;
                }
                this.dialogue.phase = Dialogue.Phase.CHOOSINGEFFECT;
            } else if (this.dialogue.phase == Dialogue.Phase.CHOOSINGEFFECT) {
                this.dialogue.phase = Dialogue.Phase.ENDING;
            } else if (this.dialogue.phase == Dialogue.Phase.ENDING) {
                Game.ui.uiScreen = Game.ui.gameScreenUI;
                Game.screens.setScreen(Game.screens.gameScreen);
                this.stateHandler.setCurrentState(NpcState.AXING);
                this.dialogue.phase = Dialogue.Phase.STARTING;
            }
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
                if (this.dialogue.phase == Dialogue.Phase.CHOOSINGEFFECT) {
                    if (Game.ui.playerDialogueUI.optionCursor.optionNum == 0) {
                        this.dialogueNode.left.left.drawEvent();
                    } else if (Game.ui.playerDialogueUI.optionCursor.optionNum == 1) {
                        this.dialogueNode.right.left.drawEvent();
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
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

}
