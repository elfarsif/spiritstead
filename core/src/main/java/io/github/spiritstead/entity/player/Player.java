package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.drawer.PlayerDrawer;
import io.github.spiritstead.entity.player.updator.Updator;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class Player implements Collidable, Moveable {
    private Updator updator;
    public Sprites sprites;
    public PlayerObjectInteractor playerObjectInteractor;
    private PlayerSolidAreaOutline playerSolidAreaOutline;
    private Interactor interactor;
    private PlayerDrawer playerDrawer;
    public int hasKey = 0;
    public Values values = new Values();
    public int spriteNum = 1;
    public ScreenPosition screenPosition = new ScreenPosition();
    public boolean collisionOn = false;
    private SolidArea solidArea;

    public Player(GamePanel gp) {
        this.sprites = new Sprites();
        this.playerDrawer = new PlayerDrawer(Game.batch, this, sprites);
        this.playerObjectInteractor = new PlayerObjectInteractor(gp, this);
        this.updator = new Updator(this, Game.aSetter.npcs);
        this.screenPosition.setX(gp.sSetting.SCREEN_WIDTH / 2 - ScreenSetting.TILE_SIZE / 2);
        this.screenPosition.setY(gp.sSetting.SCREEN_HEIGHT / 2 - ScreenSetting.TILE_SIZE / 2);
        this.solidArea = new SolidArea(5 * ScreenSetting.SCALE, 0, 6 * ScreenSetting.SCALE, 6 * ScreenSetting.SCALE);
        this.playerSolidAreaOutline = new PlayerSolidAreaOutline(gp, this);
        this.values.setDirection(Direction.DOWN);
        this.values.setSpeed(4);
        this.values.getWorldPosition().setX(ScreenSetting.TILE_SIZE * 28);
        this.values.getWorldPosition().setY(ScreenSetting.TILE_SIZE * 13);
        this.interactor = new Interactor(this);

        sprites.load();
    }

    public void interactObject(int index) {
        playerObjectInteractor.interact(index);
    }

    public void interact(NPC npc) {
        interactor.setNpc(npc);
        interactor.interact();
    }

    public void update() {
        updator.update();
    }

    public void draw() {
        playerDrawer.draw();
        playerSolidAreaOutline.draw();
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
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
    public Values getValues() {
        return this.values;
    }
}
