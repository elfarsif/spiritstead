package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.cutscene.FadeBlack;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.UIUtilities;

public class TitleSlide implements Slide {
    GameIntro gameIntro;
    Font font;
    GamePanel gp;
    String title;
    GlyphLayout layout = new GlyphLayout();
    FadeBlack fadeBlack;
    private ToolTip toolTip;

    public TitleSlide(GamePanel gp, String title, GameIntro gameIntro) {
        this.gp = gp;
        this.title = title;
        this.gameIntro = gameIntro;
        font = new Font("fonts/maruMonica.fnt");
        fadeBlack = new FadeBlack(gp);
        this.toolTip = new ToolTip(gp.batch);
    }

    @Override
    public void draw() {
        if (gp.system.keyH.spacePressed) {
            gameIntro.slideCounter++;
            gp.system.keyH.spacePressed = false;
        }
        float x = UIUtilities.getXforCenteredText(font.getBitmapFont(), layout, title, gp);
        font.getBitmapFont().draw(gp.batch, title, x, gp.sSetting.SCREEN_HEIGHT / 2);
        toolTip.draw();
        fadeBlack.draw();
    }

}
