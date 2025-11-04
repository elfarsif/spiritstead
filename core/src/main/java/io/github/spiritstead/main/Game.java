package io.github.spiritstead.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.collision.Collision;
import io.github.spiritstead.collision.TileCollision;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.screens.Screens;
import io.github.spiritstead.script.Script;
import io.github.spiritstead.tile.Tile;
import io.github.spiritstead.tile.TileManager;
import io.github.spiritstead.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public static SpriteBatch batch;
    public static KeyHandler keyH;
    public static AudioPlayer audioPlayer;
    public static UI ui;
    public static EventHandler eHandler;
    public static TileManager tileM;
    public static Resources resources;
    public static Screens screens;
    public static Script script;
    public static Player player;
    public static Dialogue dialogue;

    public Game(GamePanel gp) {
        batch = new SpriteBatch();
        script = new Script();
        keyH = new KeyHandler();
        audioPlayer = AudioPlayer.getInstance();
        eHandler = new EventHandler(gp);
        tileM = new TileManager(gp.worldSettings, new Tile[10],
                new int[gp.worldSettings.maxWorldCol][gp.worldSettings.maxWorldRow]
        );
        player = new Player(
                new Sprites(
                        new Sprite(new Texture("player/up1.png")),
                        new Sprite(new Texture("player/up2.png")),
                        new Sprite(new Texture("player/down1.png")),
                        new Sprite(new Texture("player/down2.png")),
                        new Sprite(new Texture("player/left1.png")),
                        new Sprite(new Texture("player/left2.png")),
                        new Sprite(new Texture("player/right1.png")),
                        new Sprite(new Texture("player/right2.png"))
                ),
                new ScreenPosition(ScreenSetting.SCREEN_WIDTH / 2 - ScreenSetting.TILE_SIZE / 2, ScreenSetting.SCREEN_HEIGHT / 2 - ScreenSetting.TILE_SIZE / 2),
                new SolidArea(5 * ScreenSetting.SCALE, 0, 6 * ScreenSetting.SCALE, 6 * ScreenSetting.SCALE),
                new WorldPosition(ScreenSetting.TILE_SIZE * 28, ScreenSetting.TILE_SIZE * 13),
                4,
                new Inventory(new ArrayList<>(Arrays.asList())),
                new Direction.Holder(Direction.DOWN),
                new Collision(),
                new TileCollision(),
                new Mover()
        );
        resources = new Resources();
        ui = new UI();
        screens = new Screens(gp, this.player);
        dialogue = new Dialogue();
        Gdx.input.setInputProcessor(this.keyH);
//        this.audioPlayer.playMusic(Music.THEME_SONG);
    }
}
