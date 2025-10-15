package io.github.spiritstead.ui;

/**
 * This class manages the different UI elements in different states
 */
public class UI {
    public UIScreen uiScreen;
    public GameUIScreen gameUIScreen;
    public io.github.spiritstead.ui.dialogue.DialogueUI dialogueUI;
    public DialogueUIScreen playerDialogueUIScreen;

    public UI() {
        this.gameUIScreen = new GameUIScreen();
        this.dialogueUI = new io.github.spiritstead.ui.dialogue.DialogueUI();
        this.playerDialogueUIScreen = new DialogueUIScreen();
        this.setUi(gameUIScreen);
    }

    public void draw() {
        uiScreen.draw();
    }

    public void setUi(UIScreen uiScreen) {
        this.uiScreen = uiScreen;
    }

    public void dispose() {
        gameUIScreen.dispose();
        dialogueUI.dispose();
    }
}
