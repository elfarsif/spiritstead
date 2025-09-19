package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.dialogue.DialogueUI;

/**
 * This class manages the different UI elements in different states
 */
public class UI {

    GamePanel gp;
    SpriteBatch batch;

    public UIScreen uiScreen;
    public GameScreenUI gameScreenUI;
    public DialogueUI dialogueUI;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.batch = gp.batch;
        this.gameScreenUI = new GameScreenUI(gp);
        dialogueUI = new DialogueUI(gp);
        uiScreen = gameScreenUI;
    }

    public void draw() {
        uiScreen.draw();
    }

    public void dispose() {
        gameScreenUI.dispose();
        dialogueUI.dispose();
    }
}
