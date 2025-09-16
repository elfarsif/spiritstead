package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;

public class GameIntro implements Cutscene {
    GamePanel gp;
    private SpriteBatch batch;
    int slideCounter = 0;
    ArrayList<Slide> slides = new ArrayList<>();

    public GameIntro(GamePanel gp) {
        this.gp = gp;

        slides.add(new TitleSlide(gp, "Chapter 1"));

        slides.add(new ContentSlide(gp, "intro/introSlide.png", new ArrayList<>(gp.system.script.getChapter1().get(1))));
        slides.add(new ContentSlide(gp, "intro/introSlideCharacter.png", new ArrayList<>(gp.system.script.getChapter1().get(2))));
        slides.add(new ContentSlide(gp, "intro/forrestTrail.png", new ArrayList<>(gp.system.script.getChapter1().get(3))));
        slides.add(new ContentSlide(gp, "intro/townMap.png", new ArrayList<>(gp.system.script.getChapter1().get(4))));
        slides.add(new ContentSlide(gp, "intro/introSlide.png", new ArrayList<>(gp.system.script.getChapter1().get(5))));

    }

    @Override
    public void draw(SpriteBatch batch) {
        this.batch = batch;

        if (!slides.isEmpty()) {

            Slide slide = slides.get(0);
            if (gp.system.keyH.spacePressed) {

                if (slide instanceof ContentSlide && (slide.getTextCounter() < slide.getTexts().size() - 1)) {
                    int textCounter = slide.getTextCounter() + 1;
                    slide.setTextCounter(textCounter);
                    slide.setDisplayedText("");
                    slide.setCharIndex(0);
                    slide.setCombinedText("");
                } else {
                    slideCounter++;
                    if (!slides.isEmpty()) {
                        slides.remove(0);
                    }
                }

                gp.system.keyH.spacePressed = false;
            }

            slide.draw(batch);
        } else {
            gp.screenManager.screen = gp.screenManager.gameScreen;
            gp.player.direction = Direction.LEFT;
        }

    }

}
