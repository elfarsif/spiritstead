package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.UIScreen;

public class DialogueUI implements UIScreen {
    private GamePanel gp;
    private SpriteBatch batch;
    private DialogueWindow dialogueWindow;
    public DialogueUIText text;

    public DialogueUI(GamePanel gp) {
        this.gp = gp;
        this.batch = gp.batch;
        this.dialogueWindow = new DialogueWindow(gp);
        this.text = new DialogueUIText(gp, dialogueWindow);
    }

    @Override
    public void draw() {
        dialogueWindow.draw();
        text.draw();

    }

    public void dispose() {
        dialogueWindow.dispose();
        text.dispose();
    }

}
