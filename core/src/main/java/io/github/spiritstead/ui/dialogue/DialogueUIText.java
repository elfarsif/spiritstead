package io.github.spiritstead.ui.dialogue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.tools.LetterByLetterEffect;
import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.Text;

public class DialogueUIText {
    private SpriteBatch batch;
    private DialogueWindow dialogueWindow;
    public Font font;
    public String dialogueText = "";
    public int x, y;
    private final int width;
    private TextWrapper textWrapper;
    private Text curentText;
    private Text text;
    private Text text2;

    public DialogueUIText(DialogueWindow dialogueWindow) {
        this.font = new Font("fonts/maruMonica.fnt");
        this.font.getBitmapFont().getData().setScale(0.9f);
        this.dialogueWindow = dialogueWindow;
        this.batch = Game.batch;
        this.textWrapper = new TextWrapper(font);
        this.x = dialogueWindow.x + ScreenSetting.TILE_SIZE / 2;
        this.y = dialogueWindow.y + dialogueWindow.height - ScreenSetting.TILE_SIZE;
        this.width = dialogueWindow.getWidth() - ScreenSetting.TILE_SIZE * 2;
        this.text = new Text("tesgint effect", new LetterByLetterEffect(this.font));
        this.text2 = new Text("tesgint 2nd effect", new LetterByLetterEffect(this.font));
        this.curentText = text;
    }

    public DialogueUIText(DialogueWindow dialogueWindow, int x, int y) {
        this(dialogueWindow);
        this.x = x;
        this.y = y;
    }

    public void draw() {
        curentText.draw(Game.batch, 20, 20);
        textWrapper.wrap(this.dialogueText, this.width);
        font
                .withText(textWrapper.getText())
                .atPosition(this.x, this.y)
//                .withLetterEffect(this.letterByLetterEffect)
                .draw(this.batch);
    }
    public void setTextString(String text) {
        this.dialogueText = text;
    }

    public void setText(Text text) {
        this.curentText = text;
    }

    public void dispose() {
        font.dispose();
    }

    public DialogueWindow getDialogueWindow() {
        return dialogueWindow;
    }
}
