package io.github.spiritstead.main;

import io.github.spiritstead.entity.NPC;
import io.github.spiritstead.entity.Mayor;
import io.github.spiritstead.object.*;

public class AssetSetter {
    private GamePanel gp;
    public GameObject objects[] = new GameObject[10];
    public NPC[] npcs = new NPC[10];

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        setObject();
        setNPCs();
    }

    public void setObject() {
        objects[0] = new Key();
        objects[0].getWorldPosition().setX(6 * ScreenSetting.TILE_SIZE);
        objects[0].getWorldPosition().setY(3 * ScreenSetting.TILE_SIZE);

        objects[1] = new Key();
        objects[1].getWorldPosition().setX(8 * ScreenSetting.TILE_SIZE);
        objects[1].getWorldPosition().setY(3 * ScreenSetting.TILE_SIZE);

        objects[2] = new Door();
        objects[2].getWorldPosition().setX(3 * ScreenSetting.TILE_SIZE);
        objects[2].getWorldPosition().setY(9 * ScreenSetting.TILE_SIZE);

        objects[3] = new Chest();
        objects[3].getWorldPosition().setX(4 * ScreenSetting.TILE_SIZE);
        objects[3].getWorldPosition().setY(12 * ScreenSetting.TILE_SIZE);

        objects[4] = new Boots();
        objects[4].getWorldPosition().setX(6 * ScreenSetting.TILE_SIZE);
        objects[4].getWorldPosition().setY(12 * ScreenSetting.TILE_SIZE);

        objects[5] = new Chest();
        objects[5].getWorldPosition().setX(22 * ScreenSetting.TILE_SIZE);
        objects[5].getWorldPosition().setY(17 * ScreenSetting.TILE_SIZE);

    }

    public void setNPCs() {
        npcs[0] = new Mayor(gp);
        npcs[0].getWorldPosition().setX(22 * ScreenSetting.TILE_SIZE);
        npcs[0].getWorldPosition().setY(14 * ScreenSetting.TILE_SIZE);
    }
}
