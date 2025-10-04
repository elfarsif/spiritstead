package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.audio.SoundEffect;
import io.github.spiritstead.collision.Collision;
import io.github.spiritstead.collision.TileCollisionType;
import io.github.spiritstead.main.*;
import io.github.spiritstead.object.GameObject;
import io.github.spiritstead.tools.FrameGate;

public class Player implements Collidable, Moveable {
    public Sprites sprites;
    public int xp = 0;
    private Sprite currentSprite;
    public int hasKey = 0;
    public int speed;
    private int spriteNum = 1;
    public ScreenPosition screenPosition = new ScreenPosition();
    public boolean collisionOn = false;
    private SolidArea solidArea;
    private Sprite solidAreaOutline;
    private Mover mover;
    FrameGate frameGate;
    public Direction direction;
    private Collision collision;
    private TileCollisionType tileCollision;
    public Inventory inventory = new Inventory();
    public WorldPosition worldPosition = new WorldPosition();

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
        generateSolidAreaOutline();
        sprites.load();
    }

    private void interactObject(GameObject gameObject) {
        gameObject.interact();

    }

    private void interact(NPC npc) {
        Game.ui.uiScreen = Game.ui.dialogueUI;
        Game.screens.setScreen(Game.screens.dialogueScreen);
        npc.interact();
    }

    public void update() {
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
        drawPlayer();
        this.drawSolidArea();
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
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

    @Override
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

}
