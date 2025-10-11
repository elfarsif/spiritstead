package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.Collision;
import io.github.spiritstead.collision.TileCollisionType;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.Axe;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.object.Key;
import io.github.spiritstead.object.Tree;
import io.github.spiritstead.tools.FrameGate;

import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Collidable, Moveable {
    private Sprites sprites;
    private StateHandler stateHandler;
    private Sprite currentSprite;
    private int spriteNum = 1;
    private boolean collisionOn = false;
    private SolidArea solidArea;
    private Sprite solidAreaOutline;
    private Mover mover;
    private FrameGate frameGate;
    private Collision collision;
    private TileCollisionType tileCollision;
    private WorldPosition worldPosition = new WorldPosition();
    private int axeAnimationCount = 0;
    private Animation axeAnimation;

    public Direction direction;
    public Inventory inventory;
    public int xp = 3;
    public int hasKey = 0;
    public int speed;
    public ScreenPosition screenPosition = new ScreenPosition();

    public Player(GamePanel gp) {
        this.sprites = new Sprites();
        this.screenPosition.setX(gp.sSetting.SCREEN_WIDTH / 2 - ScreenSetting.TILE_SIZE / 2);
        this.screenPosition.setY(gp.sSetting.SCREEN_HEIGHT / 2 - ScreenSetting.TILE_SIZE / 2);
        this.solidArea = new SolidArea(5 * ScreenSetting.SCALE, 0, 6 * ScreenSetting.SCALE, 6 * ScreenSetting.SCALE);
        this.direction = Direction.DOWN;
        this.speed = 4;
        this.worldPosition.setX(ScreenSetting.TILE_SIZE * 28);
        this.worldPosition.setY(ScreenSetting.TILE_SIZE * 13);
        this.mover = new Mover(this);
        this.frameGate = new FrameGate(15);
        this.collision = new Collision();
        this.tileCollision = new TileCollisionType(Game.tileM, this);
        this.stateHandler = new StateHandler(PlayerState.NORMAL);
        this.axeAnimation = new Animation(this.stateHandler, new ArrayList<>(Arrays.asList(
                new Sprite(new Texture("player/right1.png")),
                new Sprite(new Texture("player/right2.png"))
        )));
        this.inventory = new Inventory(new ArrayList<>(Arrays.asList(new Key())));
        generateSolidAreaOutline();
        sprites.load();
    }

    private void interactObject(GameObject gameObject) {
        if (gameObject instanceof Tree) {
            if (inventory.selectedItem instanceof Axe) {
                if (Game.keyH.spacePressed) {
                    Game.keyH.spacePressed = false;
                    stateHandler.setCurrentState(PlayerState.AXING);
                    gameObject.interact();
                }
            }
        } else {

            gameObject.interact();
        }

    }

    private void interact(NPC npc) {
        Game.ui.uiScreen = Game.ui.dialogueUI;
        Game.screens.setScreen(Game.screens.dialogueScreen);
        if (inventory.contains(Axe.class)) {
            npc.setDialogueNode(Game.dialogues.axeFoundDialog);
        } else {
            npc.setDialogueNode(Game.dialogues.helpFindAxe);
        }
        npc.interact();
    }

    public void update() {
        if (Game.keyH.jPressed) {
            Game.keyH.jPressed = false;
            inventory.setSelectedItemToPrev();
        }
        if (Game.keyH.kPressed) {
            Game.keyH.kPressed = false;
            inventory.setSelectedItemToNext();
        }
        if (stateHandler.isState(PlayerState.AXING)) {
//            System.out.println("animate axing");
            checkObjectCollision();

        } else if (stateHandler.isState(PlayerState.NORMAL)) {
            if (Game.keyH.upPressed || Game.keyH.downPressed || Game.keyH.leftPressed || Game.keyH.rightPressed) {
                assignKeyPressToDirection(Game.keyH);
                checkTileCollision();
                checkEventCollision();
                checkNPCCollision();
                checkObjectCollision();
                mover.move();
                updateSprite();
            }
            checkNPCCollision();
            checkObjectCollision();
        }

    }

    private void checkObjectCollision() {
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) != null && collision.check(this, Game.aSetter.obj.get(i))) {
                interactObject(Game.aSetter.obj.get(i));
            }
        }
    }
    private void checkTileCollision() {
        this.tileCollision.check();

    }
    private void updateSprite() {
        if (frameGate.tick()) {
            if (Game.player.spriteNum == 1) {
                Game.player.spriteNum = 2;
            } else if (Game.player.spriteNum == 2) {
                Game.player.spriteNum = 1;
            }
            frameGate.reset();
        }
    }
    private void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            this.direction = Direction.UP;
        } else if (keyH.downPressed) {
            this.direction = Direction.DOWN;
        } else if (keyH.leftPressed) {
            Game.player.direction = Game.player.direction.LEFT;
        } else if (keyH.rightPressed) {
            Game.player.direction = Game.player.direction.RIGHT;
        }
    }
    private void checkNPCCollision() {
        for (int i = 0; i < Game.aSetter.npcs.length - 9; i++) {
            if (collision.check(Game.player, Game.aSetter.npcs[i])) {
                interactNPC(i);
            }
        }

    }
    private void interactNPC(int npcIndex) {
        if (Game.keyH.spacePressed) {
            interact(Game.aSetter.npcs[npcIndex]);
            Game.keyH.spacePressed = false;
        }
    }
    private void checkEventCollision() {
        Game.eHandler.checkEvent();
    }
    public void draw() {
        if (stateHandler.isState(PlayerState.AXING)) {
            this.axeAnimation.drawSingleLoop(screenPosition.getX(), screenPosition.getY(), ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
        } else if (stateHandler.isState(PlayerState.NORMAL)) {
            drawPlayer();
            this.drawSolidArea();
        }

    }
    private void drawPlayer() {
        currentSprite = null;
        switch (this.direction) {
            case UP:
                currentSprite = sprites.frames.get(Direction.UP)[spriteNum - 1];
                break;
            case DOWN:
                currentSprite = sprites.frames.get(Direction.DOWN)[spriteNum - 1];
                break;
            case LEFT:
                currentSprite = sprites.frames.get(Direction.LEFT)[spriteNum - 1];
                break;
            case RIGHT:
                currentSprite = sprites.frames.get(Direction.RIGHT)[spriteNum - 1];
                break;
            default:
                currentSprite = sprites.down1;
        }
        Game.batch.draw(currentSprite, screenPosition.getX(), screenPosition.getY(), ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
    }
    private void drawSolidArea() {
        Game.batch.draw(solidAreaOutline, screenPosition.getX(), screenPosition.getY());
    }
    private void generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(
                solidArea.getRect().x,
                ScreenSetting.TILE_SIZE - solidArea.getRect().height,
                solidArea.getRect().width,
                solidArea.getRect().height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.solidAreaOutline = solidAreaSprite;
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
