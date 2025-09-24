package io.github.spiritstead.cutscene.gameIntro.contentSlide;

import io.github.spiritstead.cutscene.LetterByLetterEffect;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

public class ContentSlideText {
    GamePanel gp;
    GameIntro gameIntro;
    ContentSlide contentSlide;
    ArrayList<String> texts;
    Font font;
    private TextWrapper textWrapper;
    public int textCounter = 0;
    private String currentText = "";
    private LetterByLetterEffect letterByLetterEffect;

    public ContentSlideText(GamePanel gp, GameIntro gameIntro, ContentSlide contentSlide, ArrayList<String> text) {
        this.gp = gp;
        this.texts = text;
        this.gameIntro = gameIntro;
        this.contentSlide = contentSlide;
        font = new Font("fonts/maruMonica.fnt");
        this.textWrapper = new TextWrapper(font);
        this.letterByLetterEffect = new LetterByLetterEffect(gp, this.font);
    }

    private void displayText() {
        wrapTextToSize();
        drawLetterByLetter();
    }

    private void wrapTextToSize() {
        currentText = texts.get(textCounter);
        textWrapper.setText(currentText);
        textWrapper.setWidth((int) contentSlide.image1.getWidth());
        textWrapper.wrap();

    }

    private void drawLetterByLetter() {
        this.letterByLetterEffect.setText(textWrapper.wrappedText);
        this.letterByLetterEffect.draw(contentSlide.image1X - ScreenSetting.TILE_SIZE, contentSlide.image1Y - ScreenSetting.TILE_SIZE);
    }

    public void draw() {
        if (gp.keyH.spacePressed) {

            if (textCounter < texts.size() - 1) {
                textCounter += 1;
            } else {
                gameIntro.slideCounter++;
            }

            gp.keyH.spacePressed = false;
        }

        displayText();
    }
}
