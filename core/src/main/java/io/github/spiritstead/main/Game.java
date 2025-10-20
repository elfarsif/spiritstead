package io.github.spiritstead.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.screens.Screens;
import io.github.spiritstead.script.Script;
import io.github.spiritstead.tile.TileManager;
import io.github.spiritstead.ui.UI;

public class Game {
    public static SpriteBatch batch;
    public static KeyHandler keyH;
    public static AudioPlayer audioPlayer;
    public static UI ui;
    public static EventHandler eHandler;
    public static TileManager tileM;
    public static AssetSetter aSetter;
    public static Screens screens;
    public static Script script;
    public static Player player;
    public static Resources resources;
    public static Dialogue dialogue;

    public Game(GamePanel gp) {
        batch = new SpriteBatch();
        resources = Resources.getInstance();
        script = new Script();
        keyH = new KeyHandler(gp);
        audioPlayer = AudioPlayer.getInstance();
        eHandler = new EventHandler(gp);
        tileM = new TileManager(gp);
        aSetter = new AssetSetter(gp);
        player = new Player(gp);
        ui = new UI();
        screens = new Screens(gp, this.player);
        dialogue = new Dialogue();
        Gdx.input.setInputProcessor(this.keyH);
    }
}
