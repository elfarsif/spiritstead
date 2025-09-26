package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.ObjectColliadable;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class Player implements TileColliadable, ObjectColliadable {
    private Updator updator;
    public Sprites sprites;
    public PlayerObjectInteractor playerObjectInteractor;
    private PlayerSolidAreaOutline playerSolidAreaOutline;
    private PlayerDrawer playerDrawer;
    public int spriteNum = 1;
    public int speed;
    public ScreenPosition screenPosition = new ScreenPosition();
    private WorldPosition worldPosition = new WorldPosition();
    public boolean collisionOn = false;
    public int hasKey = 0;
    public Direction direction;
    private SolidArea solidArea;

    public Player(GamePanel gp) {
        this.sprites = new Sprites(this);
        this.playerDrawer = new PlayerDrawer(Game.batch, this, sprites);
        this.playerObjectInteractor = new PlayerObjectInteractor(gp, this);
        this.updator = new Updator(this, Game.aSetter.npcs);
        this.screenPosition.setX(gp.sSetting.SCREEN_WIDTH / 2 - ScreenSetting.TILE_SIZE / 2);
        this.screenPosition.setY(gp.sSetting.SCREEN_HEIGHT / 2 - ScreenSetting.TILE_SIZE / 2);
        this.solidArea = new SolidArea(5 * ScreenSetting.SCALE, 0, 6 * ScreenSetting.SCALE, 6 * ScreenSetting.SCALE);
        this.playerSolidAreaOutline = new PlayerSolidAreaOutline(gp, this);

        this.loadSprites();
        setDefaultPlayerValues();

    }

    public void loadSprites() {
        sprites.load();
    }

    private void setDefaultPlayerValues() {
        this.worldPosition.setX(ScreenSetting.TILE_SIZE * 28);
        this.worldPosition.setY(ScreenSetting.TILE_SIZE * 13);
        speed = 4;
        direction = direction.DOWN;
    }

    public void interactObject(int index) {
        playerObjectInteractor.interact(index);
    }

    public void interact(NPC npc) {
        Game.ui.uiScreen = Game.ui.dialogueUI;
        Game.screens.screen = Game.screens.dialogueScreen;
        npc.speak();
    }

    public void update() {
        updator.update();

    }

    public void draw() {
        playerDrawer.draw();
        playerSolidAreaOutline.draw();
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }
}
