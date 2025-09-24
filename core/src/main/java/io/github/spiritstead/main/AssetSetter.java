package io.github.spiritstead.main;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.npc.mayor.Mayor;
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
        objects[0].worldX = 6 * ScreenSetting.TILE_SIZE;
        objects[0].worldY = 3 * ScreenSetting.TILE_SIZE;

        objects[1] = new Key();
        objects[1].worldX = 8 * ScreenSetting.TILE_SIZE;
        objects[1].worldY = 3 * ScreenSetting.TILE_SIZE;

        objects[2] = new Door();
        objects[2].worldX = 3 * ScreenSetting.TILE_SIZE;
        objects[2].worldY = 9 * ScreenSetting.TILE_SIZE;

        objects[3] = new Chest();
        objects[3].worldX = 4 * ScreenSetting.TILE_SIZE;
        objects[3].worldY = 12 * ScreenSetting.TILE_SIZE;

        objects[4] = new Boots();
        objects[4].worldX = 6 * ScreenSetting.TILE_SIZE;
        objects[4].worldY = 12 * ScreenSetting.TILE_SIZE;

        objects[5] = new Chest();
        objects[5].worldX = 22 * ScreenSetting.TILE_SIZE;
        objects[5].worldY = 17 * ScreenSetting.TILE_SIZE;

    }

    public void setNPCs() {
        npcs[0] = new Mayor(gp);
        npcs[0].setWorldX(22 * ScreenSetting.TILE_SIZE);
        npcs[0].setWorldY(14 * ScreenSetting.TILE_SIZE);
    }
}
