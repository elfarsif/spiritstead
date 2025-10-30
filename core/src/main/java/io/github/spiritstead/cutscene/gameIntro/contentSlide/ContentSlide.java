package io.github.spiritstead.cutscene.gameIntro.contentSlide;

import io.github.spiritstead.cutscene.gameIntro.TextWrapper;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.tools.FadeBlack;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.cutscene.gameIntro.Slide;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.LetterByLetterEffect;
import io.github.spiritstead.tools.Sprite;

import java.util.ArrayList;

public final class ContentSlide implements Slide {
    private final Sprite sprite;
    private final FadeBlack fadeBlack;
    private final ContentSlideText contentSlideText;

    public ContentSlide(GameIntro gameIntro, ArrayList<String> texts, Sprite sprite) {
        this.fadeBlack = new FadeBlack(Game.batch);
        this.sprite = sprite;
        TextWrapper textWrapper = new TextWrapper(new Font("fonts/maruMonica.fnt"));
        ArrayList<String> wrappedTexts = new ArrayList<>();
        for (String text : texts) {
            String wrappedText = textWrapper.wrap(text, (int) sprite.getWidth());
            wrappedTexts.add(wrappedText);
        }
        this.contentSlideText = new ContentSlideText(gameIntro,
                new LetterByLetterEffect(new Font("fonts/maruMonica.fnt")),
                this.sprite.getX() - ScreenSetting.TILE_SIZE,
                this.sprite.getY() - ScreenSetting.TILE_SIZE,
                wrappedTexts
        );

    }

    public void draw() {
        this.sprite.draw();
        contentSlideText.draw();
        fadeBlack.draw();

    }

}
