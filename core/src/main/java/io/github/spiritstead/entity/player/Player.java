package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;
import io.github.spiritstead.main.ScreenSetting;

import java.awt.*;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler keyH;
    private Entity npcs[];
    public int hasKey = 0;
    public NPCInteraction NPCInteraction;
    private EntityDrawer entityDrawer;
    private EntityMover entityMover;
    private PlayerAnimator playerAnimator;
    private EntitySpriteLoader entitySpriteLoader;
    public PlayerObjectInteractor playerObjectInteractor;
    private EntityCollisionSetChecker entityCollisionSetChecker;
    private PlayerSolidAreaOutline playerSolidAreaOutline;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.npcs = gp.system.aSetter.npcs;
        this.NPCInteraction = new NPCInteraction(gp);
        this.entityDrawer = new EntityDrawer(gp, this);
        this.entityMover = new EntityMover(this);
        this.playerAnimator = new PlayerAnimator(this);
        this.entitySpriteLoader = new EntitySpriteLoader(this);
        this.playerObjectInteractor = new PlayerObjectInteractor(gp, this);
        this.entityCollisionSetChecker = new EntityCollisionSetChecker(gp, this, npcs);

        screenX = gp.sSetting.screenWidth / 2 - ScreenSetting.TILE_SIZE / 2;
        screenY = gp.sSetting.screenHeight / 2 - ScreenSetting.TILE_SIZE / 2;

        setSolidArea();

        this.playerSolidAreaOutline = new PlayerSolidAreaOutline(gp, this);

        setDefaultPlayerValues();
        entitySpriteLoader.load();

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
            entityMover.assignKeyPressToDirection(keyH);
            entityCollisionSetChecker.checkAll();
            entityMover.move();
            playerAnimator.update();
        }
    }

    public void draw() {
        entityDrawer.draw();
        playerSolidAreaOutline.draw();
    }

}
