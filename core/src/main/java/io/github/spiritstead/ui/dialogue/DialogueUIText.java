package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.InputGate;
import io.github.spiritstead.cutscene.LetterByLetterEffect;
import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class DialogueUIText {
    SpriteBatch batch;

    private DialogueWindow dialogueWindow;

    public Font font;
    public String currentDialogue = "";
    public int x, y;
    TextWrapper textWrapper;
    LetterByLetterEffect letterByLetterEffect;

    public DialogueUIText(DialogueWindow dialogueWindow) {
        this.font = new Font("fonts/maruMonica.fnt");
        this.font.getBitmapFont().getData().setScale(0.9f);
        this.dialogueWindow = dialogueWindow;
        this.batch = Game.batch;
        this.textWrapper = new TextWrapper(font);
        this.letterByLetterEffect = new LetterByLetterEffect(font);
        x = dialogueWindow.x + ScreenSetting.TILE_SIZE / 2;
        y = dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE;
    }

    public DialogueUIText(DialogueWindow dialogueWindow, int x, int y) {
        this(dialogueWindow);
        this.x = x;
        this.y = y;
    }

    public void draw() {
        wrapText();
        font.getBitmapFont().draw(batch, textWrapper.wrappedText, x, y);
    }

    private void wrapText() {
        textWrapper.setText(currentDialogue);
        textWrapper.setWidth(dialogueWindow.getWidth() - ScreenSetting.TILE_SIZE * 2);
        textWrapper.wrap();
    }

    public void dispose() {
        font.getBitmapFont().dispose();
    }

    public DialogueWindow getDialogueWindow() {
        return dialogueWindow;
    }
}
