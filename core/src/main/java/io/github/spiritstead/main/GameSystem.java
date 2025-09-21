package io.github.spiritstead.main;

import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.ui.UI;
import io.github.spiritstead.script.Script;
import io.github.spiritstead.tile.TileManager;

public class GameSystem {
    public TileManager tileM;
    public KeyHandler keyH;
    public AudioPlayer audioPlayer;
    public UI ui;
    public EventHandler eHandler;
    public Script script = new Script();
    public AssetSetter aSetter;

    public GameSystem(GamePanel gp) {
        keyH = new KeyHandler(gp);
        ui = new UI(gp);
        eHandler = new EventHandler(gp);
        tileM = new TileManager(gp);
        aSetter = new AssetSetter(gp);
        audioPlayer = new AudioPlayer();
    }
}
