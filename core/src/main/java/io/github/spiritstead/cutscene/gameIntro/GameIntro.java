package io.github.spiritstead.cutscene.gameIntro;

import io.github.spiritstead.cutscene.BlackTexture;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.cutscene.gameIntro.contentSlide.ContentSlide;
import io.github.spiritstead.cutscene.gameIntro.titleSlide.TitleSlide;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;

public class GameIntro implements Cutscene {
    GamePanel gp;
    public int slideCounter = 0;
    ArrayList<Slide> slides = new ArrayList<>();
    BlackTexture blackTexture;

    public GameIntro(GamePanel gp) {
        this.gp = gp;

        slides.add(new TitleSlide(gp, "Prologue", this));

        slides.add(new ContentSlide(gp, this, "intro/introSlide.png", new ArrayList<>(gp.script.getChapter1().get(1))));
        slides.add(new ContentSlide(gp, this, "intro/introSlideCharacter.png", new ArrayList<>(gp.script.getChapter1().get(2))));
        slides.add(new ContentSlide(gp, this, "intro/forrestTrail.png", new ArrayList<>(gp.script.getChapter1().get(3))));
        slides.add(new ContentSlide(gp, this, "intro/townMap.png", new ArrayList<>(gp.script.getChapter1().get(4))));
        slides.add(new ContentSlide(gp, this, "intro/introSlide.png", new ArrayList<>(gp.script.getChapter1().get(5))));

        this.blackTexture = new BlackTexture(gp.sSetting.SCREEN_WIDTH, gp.sSetting.SCREEN_HEIGHT);
    }

    @Override
    public void draw() {
        gp.batch.draw(blackTexture.texture, 0, 0);

        if (slideCounter < slides.size()) {
            Slide slide = slides.get(slideCounter);
            slide.draw();

        } else {
            gp.screenManager.screen = gp.screenManager.gameScreen;
            gp.player.direction = Direction.LEFT;
        }

    }

}
