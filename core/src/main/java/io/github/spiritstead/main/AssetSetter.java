package io.github.spiritstead.main;

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

    }
}
