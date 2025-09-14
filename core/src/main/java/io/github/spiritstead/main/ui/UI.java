package io.github.spiritstead.main.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.GameState;

import java.util.ArrayList;

/**
 * This class manages the different UI elements in different states
 */
public class UI {

    GamePanel gp;
    SpriteBatch batch;

    //Screens
    public TitleScreenUI titleScreenUI;
    public GameScreenUI gameScreenUI;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.titleScreenUI = new TitleScreenUI(this.gp);
        this.gameScreenUI = new GameScreenUI(this.gp);

    }

    public void draw(SpriteBatch batch) {
        this.batch = batch;

        if (gp.gameState == GameState.TITLESTATE) {
            titleScreenUI.draw(batch);
        }

        if (gp.gameState == GameState.PLAYSTATE) {
            gameScreenUI.draw(batch);
        }

        if (gp.gameState == GameState.DIALOGUE) {

        }

    }

    public void dispose() {
        gameScreenUI.dispose();
        titleScreenUI.dispose();
    }
}
