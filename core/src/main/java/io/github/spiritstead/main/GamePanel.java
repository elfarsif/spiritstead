package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.audio.AudioPlayer;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.screens.ScreenManager;
import io.github.spiritstead.script.Script;
import io.github.spiritstead.tile.TileManager;
import io.github.spiritstead.ui.UI;

/**
 * This class is the main class that handles all aspects of game logic.
 * It initialized the shared game systems and entities that are used by other classes
 **/
public class GamePanel extends ApplicationAdapter {
    public SpriteBatch batch;
    public ScreenSetting sSetting = new ScreenSetting();
    public WorldSettings worldSettings = new WorldSettings();
    public ScreenManager screenManager;
    //System
    public KeyHandler keyH;
    public AudioPlayer audioPlayer;
    public UI ui;
    public EventHandler eHandler;
    public Script script = new Script();
    public AssetSetter aSetter;
    public TileManager tileM;
    //Entities
    public Player player;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
        batch = new SpriteBatch();

        audioPlayer = new AudioPlayer();

        tileM = new TileManager(this);
        keyH = new KeyHandler(this);
        ui = new UI(this);
        eHandler = new EventHandler(this);
        aSetter = new AssetSetter(this);

        player = new Player(this, this.keyH);
        screenManager = new ScreenManager(this, player);

//        system.audioPlayer.playMusic(0);

        Gdx.input.setInputProcessor(this.keyH);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        update();
        screenManager.drawScreen();
        batch.end();
    }

    private void update() {
        if (screenManager.screen == screenManager.gameScreen) {
            screenManager.gameScreen.update();
        }

    }

    @Override
    public void resize(int width, int height) {
        // Resize the default camera viewport to match the new window size
        // This is necessary to ensure that the game scales correctly when the window is resized.
        Gdx.gl.glViewport(0, 0, sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
    }

    @Override
    public void dispose() {
        screenManager.dispose();
        batch.dispose();
    }
}
