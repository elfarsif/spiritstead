package io.github.spiritstead.main;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.Mayor;
import io.github.spiritstead.object.*;

public class AssetSetter {
    private GamePanel gp;
    public GameObject objects[] = new GameObject[10];
    public Entity npcs[] = new Entity[10];

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        setObject();
        setNPCs();
    }

    public void setObject() {
        objects[0] = new Key();
        objects[0].worldX = 6 * gp.sSetting.tileSize;
        objects[0].worldY = 3 * gp.sSetting.tileSize;

        objects[1] = new Key();
        objects[1].worldX = 8 * gp.sSetting.tileSize;
        objects[1].worldY = 3 * gp.sSetting.tileSize;

        objects[2] = new Door();
        objects[2].worldX = 3 * gp.sSetting.tileSize;
        objects[2].worldY = 9 * gp.sSetting.tileSize;

        objects[3] = new Chest();
        objects[3].worldX = 4 * gp.sSetting.tileSize;
        objects[3].worldY = 12 * gp.sSetting.tileSize;

        objects[4] = new Boots();
        objects[4].worldX = 6 * gp.sSetting.tileSize;
        objects[4].worldY = 12 * gp.sSetting.tileSize;

        objects[5] = new Chest();
        objects[5].worldX = 22 * gp.sSetting.tileSize;
        objects[5].worldY = 17 * gp.sSetting.tileSize;

    }

    public void setNPCs() {
        npcs[0] = new Mayor(gp);
        npcs[0].worldX = 22 * gp.sSetting.tileSize;
        npcs[0].worldY = 14 * gp.sSetting.tileSize;
    }
}
