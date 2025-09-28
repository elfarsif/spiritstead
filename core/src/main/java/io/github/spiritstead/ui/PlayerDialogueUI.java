package io.github.spiritstead.ui;

import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.screens.OptionCursor;
import io.github.spiritstead.ui.dialogue.DialogueUIText;
import io.github.spiritstead.ui.dialogue.DialogueWindow;

public class PlayerDialogueUI implements UIScreen {
    private DialogueWindow dialogueWindow;
    public DialogueUIText dialogueUIText1;
    public DialogueUIText dialogueUIText2;
    public OptionCursor optionCursor;

    public PlayerDialogueUI() {
        this.dialogueWindow = new DialogueWindow(
            ScreenSetting.TILE_SIZE * 2,
            ScreenSetting.TILE_SIZE / 2,
            ScreenSetting.SCREEN_WIDTH - ScreenSetting.TILE_SIZE * 4,
            ScreenSetting.TILE_SIZE * 3);
        this.dialogueUIText1 = new DialogueUIText(
            dialogueWindow,
            dialogueWindow.x + ScreenSetting.TILE_SIZE / 2,
            dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE / 2
        );
        this.dialogueUIText2 = new DialogueUIText(
            dialogueWindow,
            dialogueWindow.x + ScreenSetting.TILE_SIZE / 2,
            dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE * 2
        );
        this.optionCursor = new OptionCursor(this.dialogueUIText1.font);
        this.dialogueUIText1.currentDialogue = "testing ui";
        this.dialogueUIText2.currentDialogue = "testing 2";
    }

    @Override
    public void draw() {
        dialogueWindow.draw();
        if (optionCursor.optionNum == 0) {
            optionCursor.draw(this.dialogueUIText1.x - 10, this.dialogueUIText1.y);
        } else if (optionCursor.optionNum == 1) {
            optionCursor.draw(this.dialogueUIText2.x - 10, this.dialogueUIText2.y);
        }
        dialogueUIText1.draw();
        dialogueUIText2.draw();
    }
}
