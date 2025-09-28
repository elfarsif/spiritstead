package io.github.spiritstead.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.Game;
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
    public PlayerDialogueUI playerDialogueUI;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.batch = Game.batch;
        this.gameScreenUI = new GameScreenUI(gp);
        this.dialogueUI = new DialogueUI(gp);
        this.playerDialogueUI = new PlayerDialogueUI();

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
