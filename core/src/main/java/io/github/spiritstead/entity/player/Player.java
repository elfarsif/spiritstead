package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.ObjectColliadable;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;
import java.util.EnumMap;

public class Player implements TileColliadable, ObjectColliadable {
    private GamePanel gp;
    private KeyHandler keyH;
    private NPC npcs[];
    public int hasKey = 0;
    public NPCInteraction NPCInteraction;
    private PlayerDrawer playerDrawer;
    private PlayerMover playerMover;
    private PlayerAnimator playerAnimator;
    private PlayerSpriteLoader playerSpriteLoader;
    public PlayerObjectInteractor playerObjectInteractor;
    private PlayerCollisionHandler playerCollisionHandler;
    private PlayerSolidAreaOutline playerSolidAreaOutline;

    //entity
    public int worldX, worldY;
    public int speed;

    public Sprite up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int spriteNum = 1;

    public Rectangle solidArea;
    //allows to change solid area for collision detection and store original values to restore area
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public boolean collisionOn = false;

    public EnumMap<Direction, Sprite[]> frames = new EnumMap<>(Direction.class);

    public int screenX;
    public int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.npcs = gp.system.aSetter.npcs;
        this.NPCInteraction = new NPCInteraction(gp);
        this.playerDrawer = new PlayerDrawer(gp.batch, this);
        this.playerMover = new PlayerMover(this);
        this.playerAnimator = new PlayerAnimator(this);
        this.playerSpriteLoader = new PlayerSpriteLoader(this);
        this.playerObjectInteractor = new PlayerObjectInteractor(gp, this);
        this.playerCollisionHandler = new PlayerCollisionHandler(gp, this, npcs);

        screenX = gp.sSetting.SCREEN_WIDTH / 2 - ScreenSetting.TILE_SIZE / 2;
        screenY = gp.sSetting.SCREEN_HEIGHT / 2 - ScreenSetting.TILE_SIZE / 2;

        setSolidArea();

        this.playerSolidAreaOutline = new PlayerSolidAreaOutline(gp, this);

        setDefaultPlayerValues();
        playerSpriteLoader.load();

        frames.put(Direction.UP, new Sprite[]{up1, up2});
        frames.put(Direction.DOWN, new Sprite[]{down1, down2});
        frames.put(Direction.LEFT, new Sprite[]{left1, left2});
        frames.put(Direction.RIGHT, new Sprite[]{right1, right2});

    }

    private void setSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 5 * gp.sSetting.SCALE;
        solidArea.y = 0;
        //record default values to change solid area
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 6 * gp.sSetting.SCALE;
        solidArea.height = 6 * gp.sSetting.SCALE;
    }

    private void setDefaultPlayerValues() {
        worldX = ScreenSetting.TILE_SIZE * 28;
        worldY = ScreenSetting.TILE_SIZE * 13;
        speed = 4;
        direction = direction.DOWN;
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            playerMover.assignKeyPressToDirection(keyH);
            playerCollisionHandler.checkAll();
            playerMover.move();
            playerAnimator.update();
        }
    }

    public void draw() {
        playerDrawer.draw();
        playerSolidAreaOutline.draw();
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public int getSolidAreadDefaultX() {
        return this.solidAreaDefaultX;
    }

    @Override
    public int getSolidAreadDefaultY() {
        return this.solidAreaDefaultY;
    }

    @Override
    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public int getWorldX() {
        return this.worldX;
    }

    @Override
    public int getWorldY() {
        return this.worldY;
    }

    @Override
    public Rectangle getSolidArea() {
        return this.solidArea;
    }

    @Override
    public void setCollisonOn(boolean collisonOn) {
        this.collisionOn = collisonOn;
    }

}
