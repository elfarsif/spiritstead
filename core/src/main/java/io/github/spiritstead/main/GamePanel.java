package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.screens.ScreenManager;

/**
 * This class is the main class that handles all aspects of game logic.
 * It initialized the shared game systems and entities that are used by other classes
 **/
public class GamePanel extends ApplicationAdapter {
    public SpriteBatch batch;
    public ScreenSetting sSetting = new ScreenSetting();
    public WorldSettings worldSettings = new WorldSettings();
    public ScreenManager screenManager;
    public GameSystem system;

    //Entities
    public Player player;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(sSetting.screenWidth, sSetting.screenHeight);
        batch = new SpriteBatch();

        system = new GameSystem(this);
        player = new Player(this, system.keyH);
        screenManager = new ScreenManager(this, system, player);

//        system.audioPlayer.playMusic(0);

        Gdx.input.setInputProcessor(system.keyH);
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
        Gdx.gl.glViewport(0, 0, sSetting.screenWidth, sSetting.screenHeight);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, sSetting.screenWidth, sSetting.screenHeight);
    }

    @Override
    public void dispose() {
        screenManager.dispose();
        batch.dispose();
    }
}
