package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * This class is the main class that handles all aspects of game logic.
 * It initialized the shared game systems and entities that are used by other classes
 **/
public class GamePanel extends ApplicationAdapter {
    public Game game;
    public ScreenSetting sSetting = new ScreenSetting();
    public WorldSettings worldSettings = new WorldSettings();

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
        this.game = new Game(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Game.batch.begin();
        update();
        Game.screens.drawScreen();
        Game.batch.end();
    }

    private void update() {
        if (Game.screens.screen == Game.screens.gameScreen) {
            Game.screens.gameScreen.update();
        }

    }

    @Override
    public void resize(int width, int height) {
        // Resize the default camera viewport to match the new window size
        // This is necessary to ensure that the game scales correctly when the window is resized.
        Gdx.gl.glViewport(0, 0, sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
        Game.batch.getProjectionMatrix().setToOrtho2D(0, 0, sSetting.SCREEN_WIDTH, sSetting.SCREEN_HEIGHT);
    }

    @Override
    public void dispose() {
        Game.screens.dispose();
        Game.batch.dispose();
    }
}
