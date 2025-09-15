package io.github.spiritstead.main.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;

/**
 * This class manages the different UI elements in different states
 */
public class UI {

    GamePanel gp;
    SpriteBatch batch;

    //Screens
    public TitleScreen titleScreen;
    public GameScreenUI gameScreenUI;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.titleScreen = new TitleScreen(this.gp);
        this.gameScreenUI = new GameScreenUI(this.gp);

    }

    public void draw(SpriteBatch batch) {
        this.batch = batch;

        if (gp.screen == gp.playScreen) {
            gameScreenUI.draw(batch);
        }

    }

    public void dispose() {
        gameScreenUI.dispose();
        titleScreen.dispose();
    }
}
