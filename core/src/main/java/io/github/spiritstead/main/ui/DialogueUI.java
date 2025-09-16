package io.github.spiritstead.main.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;

public class DialogueUI implements UIScreen {
    private GamePanel gp;
    private SpriteBatch batch;
    private DialogueWindow dialogueWindow;

    public DialogueUI(GamePanel gp) {
        this.gp = gp;
        this.batch = gp.batch;
        this.dialogueWindow = new DialogueWindow(gp);

    }

    @Override
    public void draw() {
        batch.draw(dialogueWindow.texture, dialogueWindow.x, dialogueWindow.y, dialogueWindow.width, dialogueWindow.height);

    }

    public void dispose() {
    }

}
