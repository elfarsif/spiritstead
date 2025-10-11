package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.dialogue.DialogueUI;

/**
 * This class manages the different UI elements in different states
 */
public class UI {
    public UIScreen uiScreen;
    public GameScreenUI gameScreenUI;
    public DialogueUI dialogueUI;
    public PlayerDialogueUI playerDialogueUI;

    public UI() {
        this.gameScreenUI = new GameScreenUI();
        this.dialogueUI = new DialogueUI();
        this.playerDialogueUI = new PlayerDialogueUI();
        this.setUiScreen(gameScreenUI);
    }

    public void draw() {
        uiScreen.draw();
    }

    public void setUiScreen(UIScreen uiScreen) {
        this.uiScreen = uiScreen;
    }

    public void dispose() {
        gameScreenUI.dispose();
        dialogueUI.dispose();
    }
}
