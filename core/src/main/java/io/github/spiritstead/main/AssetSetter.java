package io.github.spiritstead.main;

import io.github.spiritstead.entity.Mayor;
import io.github.spiritstead.object.Boots;
import io.github.spiritstead.object.Chest;
import io.github.spiritstead.object.Door;
import io.github.spiritstead.object.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.objects[0] = new Key();
        gp.objects[0].worldX = 6 *gp.tileSize;
        gp.objects[0].worldY = 3 *gp.tileSize;

        gp.objects[1] = new Key();
        gp.objects[1].worldX = 8 *gp.tileSize;
        gp.objects[1].worldY = 3 *gp.tileSize;

        gp.objects[2] = new Door();
        gp.objects[2].worldX = 3 *gp.tileSize;
        gp.objects[2].worldY = 9 *gp.tileSize;

        gp.objects[3] = new Chest();
        gp.objects[3].worldX = 4 *gp.tileSize;
        gp.objects[3].worldY = 12 *gp.tileSize;

        gp.objects[4] = new Boots();
        gp.objects[4].worldX = 6 *gp.tileSize;
        gp.objects[4].worldY = 12 *gp.tileSize;

        gp.objects[5] = new Chest();
        gp.objects[5].worldX = 22 *gp.tileSize;
        gp.objects[5].worldY = 17 *gp.tileSize;

    }

    public void setNPCs(){
        gp.npcs[0] = new Mayor(gp);
        gp.npcs[0].worldX = 22*gp.tileSize;
        gp.npcs[0].worldY = 14*gp.tileSize;
    }
}
