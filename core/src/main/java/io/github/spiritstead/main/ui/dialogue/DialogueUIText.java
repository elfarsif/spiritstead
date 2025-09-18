package io.github.spiritstead.main.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ui.UIUtilities;

public class DialogueUIText {
    GamePanel gp;
    SpriteBatch batch;
    DialogueWindow dialogueWindow;
    private BitmapFont font;
    public String currentDialogue = "";
    int x, y;

    public DialogueUIText(GamePanel gp, DialogueWindow dialogueWindow) {
        this.font = UIUtilities.initializeFont(font, "fonts/maruMonica.fnt");
        this.gp = gp;
        this.dialogueWindow = dialogueWindow;
        this.batch = gp.batch;

    }

    public void draw() {
        x = dialogueWindow.x + gp.sSetting.tileSize;
        y = dialogueWindow.y + dialogueWindow.height - gp.sSetting.tileSize;
        font.getData().setScale(1f);
        font.draw(batch, currentDialogue, x, y);
    }

    public void dispose() {
        font.dispose();
    }

}
