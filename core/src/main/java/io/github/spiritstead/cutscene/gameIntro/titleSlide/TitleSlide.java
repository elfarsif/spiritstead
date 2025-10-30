package io.github.spiritstead.cutscene.gameIntro.titleSlide;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tools.FadeBlack;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.cutscene.gameIntro.Slide;
import io.github.spiritstead.cutscene.gameIntro.ToolTip;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.tools.UIUtilities;

public final class TitleSlide implements Slide {
    private final GameIntro gameIntro;
    private final Font font;
    private final String title;
    private final GlyphLayout layout = new GlyphLayout();
    private final FadeBlack fadeBlack;
    private final ToolTip toolTip;

    public TitleSlide(String title, GameIntro gameIntro, ToolTip toolTip, Font font, FadeBlack fadeBlack) {
        this.title = title;
        this.gameIntro = gameIntro;
        this.font = font;
        this.fadeBlack = fadeBlack;
        this.toolTip = toolTip;
    }

    @Override
    public void draw() {
        if (Game.keyH.spacePressed) {
            gameIntro.slideCounter++;
            Game.keyH.spacePressed = false;
        }
        float x = UIUtilities.getXforCenteredText(font.getBitmapFont(), layout, title);
        font.getBitmapFont().draw(Game.batch, title, x, ScreenSetting.SCREEN_HEIGHT / 2);
        toolTip.draw(Game.batch);
        fadeBlack.draw();
    }

}
