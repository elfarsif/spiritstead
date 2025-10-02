package io.github.spiritstead.ui;

import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.OptionCursor;
import io.github.spiritstead.tools.Options;
import io.github.spiritstead.ui.dialogue.DialogueUIText;
import io.github.spiritstead.ui.dialogue.DialogueWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerDialogueUI implements UIScreen {
    private DialogueWindow dialogueWindow;
    public DialogueUIText dialogueUIText1;
    public DialogueUIText dialogueUIText2;
    public OptionCursor optionCursor;
    private Options options;

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
        this.options = new Options(dialogueUIText1.font, ScreenSetting.TILE_SIZE);
    }

    @Override
    public void draw() {
        dialogueWindow.draw();
        options.setList(new ArrayList<String>(Arrays.asList(
            dialogueUIText1.currentDialogue,
            dialogueUIText2.currentDialogue
        )));
        options.draw(dialogueUIText1.x, dialogueUIText1.y);
        if (optionCursor.optionNum == 0) {
            optionCursor.draw(this.dialogueUIText1.x - 15, this.dialogueUIText1.y);
        } else if (optionCursor.optionNum == 1) {
            optionCursor.draw(this.dialogueUIText2.x - 15, this.options.y - options.yDiff);
        }
    }
}
