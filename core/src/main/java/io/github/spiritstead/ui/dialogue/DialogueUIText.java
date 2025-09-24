package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class DialogueUIText {
    GamePanel gp;
    SpriteBatch batch;

    private DialogueWindow dialogueWindow;

    public Font font;
    public String currentDialogue = "";
    int x, y;

    public DialogueUIText(GamePanel gp, DialogueWindow dialogueWindow) {
        this.font = new Font("fonts/maruMonica.fnt");
        this.gp = gp;
        this.dialogueWindow = dialogueWindow;
        this.batch = gp.batch;

    }

    public void draw() {
        x = dialogueWindow.x + ScreenSetting.TILE_SIZE / 2;
        y = dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE;
        font.getBitmapFont().getData().setScale(0.9f);
        font.getBitmapFont().draw(batch, currentDialogue, x, y);
    }

    public void dispose() {
        font.getBitmapFont().dispose();
    }

    public DialogueWindow getDialogueWindow() {
        return dialogueWindow;
    }
}
