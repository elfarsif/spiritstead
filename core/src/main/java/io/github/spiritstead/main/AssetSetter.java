package io.github.spiritstead.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.object.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssetSetter {
    public List<GameObject> obj = new ArrayList<>();
    public NPC[] npcs = new NPC[10];
    public final GameObjects gameObjects;

    public AssetSetter() {
        setObject();
        setNPCs();
        this.gameObjects = new GameObjects(obj);
    }

    public void setObject() {
        obj.add(new Key(6 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Key(8 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Door(3 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(4 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Boots(6 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
//        obj.add(new Chest(22 * ScreenSetting.TILE_SIZE, 17 * ScreenSetting.TILE_SIZE));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 13 * ScreenSetting.TILE_SIZE)));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 14 * ScreenSetting.TILE_SIZE)));
    }

    public void setNPCs() {
        npcs[0] = new Mayor(new Sprites(
                new Sprite(new Texture("player/up1.png")),
                new Sprite(new Texture("player/up2.png")),
                new Sprite(new Texture("player/down1.png")),
                new Sprite(new Texture("player/down2.png")),
                new Sprite(new Texture("player/left1.png")),
                new Sprite(new Texture("player/left2.png")),
                new Sprite(new Texture("player/right1.png")),
                new Sprite(new Texture("player/right2.png"))
        ),
                Game.script.mayorDialogue,
                new SolidArea(0, 0, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE),
                1
        );
        npcs[0].getWorldPosition().setX(22 * ScreenSetting.TILE_SIZE);
        npcs[0].getWorldPosition().setY(14 * ScreenSetting.TILE_SIZE);
    }
}
