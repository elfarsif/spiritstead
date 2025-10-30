package io.github.spiritstead.cutscene.gameIntro.contentSlide;

import io.github.spiritstead.tools.LetterByLetterEffect;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

public final class ContentSlideText {
    private final GameIntro gameIntro;
    private final ArrayList<String> wrappedTexts;
    private final LetterByLetterEffect letterByLetterEffect;
    private final Float x, y;

    private int textCounter = 0;

    public ContentSlideText(GameIntro gameIntro, LetterByLetterEffect letterByLetterEffect, Float x, Float y,
                            ArrayList<String> wrappedTexts) {
        this.gameIntro = gameIntro;
        this.wrappedTexts = wrappedTexts;
        this.letterByLetterEffect = letterByLetterEffect;
        this.x = x;
        this.y = y;
    }

    private void displayText() {
        this.drawLetterByLetter();
    }

    private void drawLetterByLetter() {
        this.letterByLetterEffect.setText(this.wrappedTexts.get(this.textCounter));
        this.letterByLetterEffect.draw(this.x, this.y);
    }

    public void draw() {
        if (Game.keyH.spacePressed) {

            if (this.textCounter < wrappedTexts.size() - 1) {
                this.textCounter += 1;
                this.letterByLetterEffect.reset();
            } else {
                this.gameIntro.slideCounter++;
            }

            Game.keyH.spacePressed = false;
        }

        this.displayText();
    }
}
