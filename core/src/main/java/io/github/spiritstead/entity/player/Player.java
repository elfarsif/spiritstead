package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler keyH;
    private Entity npcs[];
    private Sprite solidAreaOutlineSprite;
    public int hasKey = 0;
    public NPCInteraction NPCInteraction;
    private EntityRenderer entityRenderer;
    private EntityMover entityMover;
    private EntityAnimator entityAnimator;
    private EntitySpriteLoader entitySpriteLoader;
    public PlayerObjectInteractor playerObjectInteractor;
    private EntityCollisionSetChecker entityCollisionSetChecker;
    private PlayerSolidAreaOutline playerSolidAreaOutline;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.npcs = gp.system.aSetter.npcs;
        this.NPCInteraction = new NPCInteraction(gp);
        this.entityRenderer = new EntityRenderer(gp, this);
        this.entityMover = new EntityMover(this, keyH);
        this.entityAnimator = new EntityAnimator(this);
        this.entitySpriteLoader = new EntitySpriteLoader(this);
        this.playerObjectInteractor = new PlayerObjectInteractor(gp, this);
        this.entityCollisionSetChecker = new EntityCollisionSetChecker(gp, this, npcs);

        screenX = gp.sSetting.screenWidth / 2 - gp.sSetting.tileSize / 2;
        screenY = gp.sSetting.screenHeight / 2 - gp.sSetting.tileSize / 2;

        setSolidArea();

        this.playerSolidAreaOutline = new PlayerSolidAreaOutline(gp, this);

        setDefaultPlayerValues();
        entitySpriteLoader.load();
    }

    private void setSolidArea() {
        solidArea = new Rectangle();
        solidArea.x = 5 * gp.sSetting.scale;
        solidArea.y = 0;
        //record default values to change solid area
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 6 * gp.sSetting.scale;
        solidArea.height = 6 * gp.sSetting.scale;
    }

    private void setDefaultPlayerValues() {
        worldX = gp.sSetting.tileSize * 28;
        worldY = gp.sSetting.tileSize * 13;
        speed = 4;
        direction = direction.DOWN;
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            entityMover.assignKeyPressToDirection();
            entityCollisionSetChecker.checkAll();
            entityMover.move();
            entityAnimator.update();
        }
    }

    public void draw() {
        entityRenderer.draw();
        playerSolidAreaOutline.draw();
    }

}
