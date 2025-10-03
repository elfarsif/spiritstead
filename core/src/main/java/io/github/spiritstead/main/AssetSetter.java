package io.github.spiritstead.main;

import io.github.spiritstead.entity.NPC;
import io.github.spiritstead.entity.Mayor;
import io.github.spiritstead.object.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AssetSetter {
    private GamePanel gp;
    public GameObject objects[] = new GameObject[10];
    public ArrayList<GameObject> obj = new ArrayList<>();
    public NPC[] npcs = new NPC[10];

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        setObject();
        setNPCs();
    }

    public void setObject() {
        objects[0] = new Key(6 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE);
        objects[1] = new Key(8 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE);
        objects[2] = new Door(3 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE);
        objects[3] = new Chest(4 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE);
        objects[4] = new Boots(6 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE);
        objects[5] = new Chest(22 * ScreenSetting.TILE_SIZE, 17 * ScreenSetting.TILE_SIZE);
        objects[6] = new Axe(25 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE);

        obj.add(new Key(6 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Key(8 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Door(3 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(4 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Boots(6 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(22 * ScreenSetting.TILE_SIZE, 17 * ScreenSetting.TILE_SIZE));
        obj.add(new Axe(25 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));

    }

    public void setNPCs() {
        npcs[0] = new Mayor(gp);
        npcs[0].getWorldPosition().setX(22 * ScreenSetting.TILE_SIZE);
        npcs[0].getWorldPosition().setY(14 * ScreenSetting.TILE_SIZE);
    }
}
