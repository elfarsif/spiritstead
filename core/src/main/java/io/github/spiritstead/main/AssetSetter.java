package io.github.spiritstead.main;

import io.github.spiritstead.entity.NPC;
import io.github.spiritstead.entity.Mayor;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.object.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AssetSetter {
    private GamePanel gp;
    public ArrayList<GameObject> obj = new ArrayList<>();
    public NPC[] npcs = new NPC[10];

    public NPC mayor;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        setObject();
        setNPCs();
    }

    public void setObject() {
        obj.add(new Key(6 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Key(8 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Door(3 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(4 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Boots(6 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(22 * ScreenSetting.TILE_SIZE, 17 * ScreenSetting.TILE_SIZE));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 13 * ScreenSetting.TILE_SIZE)));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 14 * ScreenSetting.TILE_SIZE)));
    }

    public void setNPCs() {
        npcs[0] = new Mayor(gp);
        mayor = npcs[0];
        npcs[0].getWorldPosition().setX(22 * ScreenSetting.TILE_SIZE);
        npcs[0].getWorldPosition().setY(14 * ScreenSetting.TILE_SIZE);
    }
}
