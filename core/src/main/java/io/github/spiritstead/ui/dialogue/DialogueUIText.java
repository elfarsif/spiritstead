package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class DialogueUIText {
    GamePanel gp;
    SpriteBatch batch;
    DialogueWindow dialogueWindow;
    private Font font;
    public String currentDialogue = "";
    int x, y;

    public DialogueUIText(GamePanel gp, DialogueWindow dialogueWindow) {
        this.font = new Font("fonts/maruMonica.fnt");
        this.gp = gp;
        this.dialogueWindow = dialogueWindow;
        this.batch = gp.batch;

    }

    public void draw() {
        x = dialogueWindow.x + ScreenSetting.TILE_SIZE;
        y = dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE;
        font.getBitmapFont().getData().setScale(1f);
        font.getBitmapFont().draw(batch, currentDialogue, x, y);
    }

    public void dispose() {
        font.getBitmapFont().dispose();
    }

}
