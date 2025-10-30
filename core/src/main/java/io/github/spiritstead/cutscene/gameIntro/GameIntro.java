package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.BlackTexture;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.cutscene.gameIntro.contentSlide.ContentSlide;
import io.github.spiritstead.cutscene.gameIntro.titleSlide.TitleSlide;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.tools.FadeBlack;
import io.github.spiritstead.tools.Sprite;

import java.util.ArrayList;

public final class GameIntro implements Cutscene {
    public int slideCounter = 0;
    ArrayList<Slide> slides = new ArrayList<>();
    BlackTexture blackTexture;

    public GameIntro(GamePanel gp) {

        slides.add(new TitleSlide(
                "Prologue",
                this,
                new ToolTip(
                        new Font("fonts/maruMonica.fnt"),
                        "[ press space ]"
                ),

                new Font("fonts/maruMonica.fnt"),
                new FadeBlack(Game.batch)
        ));

        slides.add(new ContentSlide(this, new ArrayList<>(Game.script.getChapter1().get(1)),
                new Sprite(
                        "intro/introSlide.png",
                        ScreenSetting.TILE_SIZE * 10,
                        ScreenSetting.TILE_SIZE * 6
                )
        ));
        slides.add(new ContentSlide(this, new ArrayList<>(Game.script.getChapter1().get(2)),
                new Sprite(
                        "intro/introSlideCharacter.png",
                        ScreenSetting.TILE_SIZE * 10,
                        ScreenSetting.TILE_SIZE * 6
                )
        ));
        slides.add(new ContentSlide(this, new ArrayList<>(Game.script.getChapter1().get(3)),
                new Sprite(
                        "intro/forrestTrail.png",
                        ScreenSetting.TILE_SIZE * 10,
                        ScreenSetting.TILE_SIZE * 6
                )));
        slides.add(new ContentSlide(this, new ArrayList<>(Game.script.getChapter1().get(4)),
                new Sprite(
                        "intro/townMap.png",
                        ScreenSetting.TILE_SIZE * 10,
                        ScreenSetting.TILE_SIZE * 6
                )
        ));
        slides.add(new ContentSlide(this, new ArrayList<>(Game.script.getChapter1().get(5)),
                new Sprite(
                        "intro/introSlide.png",
                        ScreenSetting.TILE_SIZE * 10,
                        ScreenSetting.TILE_SIZE * 6
                )));

        this.blackTexture = new BlackTexture(gp.sSetting.SCREEN_WIDTH, gp.sSetting.SCREEN_HEIGHT);
    }

    @Override
    public void draw() {
        Game.batch.draw(blackTexture.texture, 0, 0);

        if (slideCounter < slides.size()) {
            Slide slide = slides.get(slideCounter);
            slide.draw();

        } else {
            Game.screens.setScreen(Game.screens.gameScreen);
//            Game.player.direction = Direction.LEFT;
        }

    }

}
