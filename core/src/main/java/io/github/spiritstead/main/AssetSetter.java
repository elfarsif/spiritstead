package io.github.spiritstead.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.collision.Collision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.object.*;
import io.github.spiritstead.tools.FrameGate;

import java.util.*;

public class AssetSetter {
    private final List<GameObject> obj = new ArrayList<>();
    private final NPC[] npcsArray = new NPC[10];
    private EventBus eventBus;

    public final Npcs npcs;
    public final GameObjects gameObjects;

    public AssetSetter() {
        this.setNPCs();
        this.npcs = new Npcs(this.npcsArray);
        this.eventBus = new EventBus(EventType.TREE_REMOVED);
        this.setObject();
        this.gameObjects = new GameObjects(obj);
        this.eventBus.subscribe(EventType.TREE_REMOVED, this.npcs.get(0));
        this.eventBus.subscribe(EventType.TREE_REMOVED, this.gameObjects);
    }

    public void setNPCs() {
        npcsArray[0] = new Mayor(new Sprites(
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
                1,
                new Collision(),
                new Direction.Holder(Direction.LEFT),
                new State<NpcState>(NpcState.MOVING),
                Animation.looping(new FrameGate(30), new ArrayList<>(Arrays.asList(
                        new Sprite(new Texture("player/down1.png")),
                        new Sprite(new Texture("player/down2.png"))
                ))),
                Animation.looping(new FrameGate(30), new ArrayList<>(Arrays.asList(
                        new Sprite(new Texture("player/right1.png"))
                ))),
                Action.move(Direction.LEFT, 120),
                new TileCollision(),
                new Mover(),
                new WorldPosition(22 * ScreenSetting.TILE_SIZE, 14 * ScreenSetting.TILE_SIZE),
                Action.move(Direction.DOWN, 120)
        );
    }
    public void setObject() {
        obj.add(new Key(6 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Key(8 * ScreenSetting.TILE_SIZE, 3 * ScreenSetting.TILE_SIZE));
        obj.add(new Door(3 * ScreenSetting.TILE_SIZE, 9 * ScreenSetting.TILE_SIZE));
        obj.add(new Chest(4 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
        obj.add(new Boots(6 * ScreenSetting.TILE_SIZE, 12 * ScreenSetting.TILE_SIZE));
//        obj.add(new Chest(22 * ScreenSetting.TILE_SIZE, 17 * ScreenSetting.TILE_SIZE));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 13 * ScreenSetting.TILE_SIZE),
                this.eventBus));
        obj.add(new Tree(new WorldPosition(21 * ScreenSetting.TILE_SIZE, 14 * ScreenSetting.TILE_SIZE),
                this.eventBus));
    }
}
