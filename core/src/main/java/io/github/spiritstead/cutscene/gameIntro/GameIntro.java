package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.cutscene.BlackTexture;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;

public class GameIntro implements Cutscene {
    GamePanel gp;
    int slideCounter = 0;
    ArrayList<Slide> slides = new ArrayList<>();
    BlackTexture blackTexture;

    public GameIntro(GamePanel gp) {
        this.gp = gp;

        slides.add(new TitleSlide(gp, "Prologue"));

        slides.add(new ContentSlide(gp, "intro/introSlide.png", new ArrayList<>(gp.system.script.getChapter1().get(1))));
        slides.add(new ContentSlide(gp, "intro/introSlideCharacter.png", new ArrayList<>(gp.system.script.getChapter1().get(2))));
        slides.add(new ContentSlide(gp, "intro/forrestTrail.png", new ArrayList<>(gp.system.script.getChapter1().get(3))));
        slides.add(new ContentSlide(gp, "intro/townMap.png", new ArrayList<>(gp.system.script.getChapter1().get(4))));
        slides.add(new ContentSlide(gp, "intro/introSlide.png", new ArrayList<>(gp.system.script.getChapter1().get(5))));

        this.blackTexture = new BlackTexture(gp.sSetting.screenWidth, gp.sSetting.screenHeight);
    }

    @Override
    public void draw() {
        gp.batch.draw(blackTexture.texture, 0, 0);

        if (slideCounter < slides.size()) {

            Slide slide = slides.get(slideCounter);

            if (gp.system.keyH.spacePressed) {

                if (slide instanceof ContentSlide && (slide.getTextCounter() < slide.getTexts().size() - 1)) {
                    int textCounter = slide.getTextCounter() + 1;
                    slide.setTextCounter(textCounter);
                    slide.setDisplayedText("");
                    slide.setCharIndex(0);
                    slide.setCombinedText("");
                } else {
                    slideCounter++;
                }
                fadeInTransition();

                gp.system.keyH.spacePressed = false;
            }

            slide.draw();
        } else {
            gp.screenManager.screen = gp.screenManager.gameScreen;
            gp.player.direction = Direction.LEFT;
        }

    }

    private void fadeInTransition() {
    }

}
