package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.cutscene.FadeBlack;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.UIUtilities;

public class TitleSlide implements Slide {
    GameIntro gameIntro;
    BitmapFont font;
    GamePanel gp;
    String title;
    GlyphLayout layout = new GlyphLayout();
    FadeBlack fadeBlack;

    public TitleSlide(GamePanel gp, String title, GameIntro gameIntro) {
        this.gp = gp;
        this.title = title;
        this.gameIntro = gameIntro;
        font = UIUtilities.initializeFont(font, "fonts/maruMonica.fnt");
        fadeBlack = new FadeBlack(gp);
    }

    @Override
    public void draw() {
        if (gp.system.keyH.spacePressed) {
            gameIntro.slideCounter++;
            gp.system.keyH.spacePressed = false;
        }
        float x = UIUtilities.getXforCenteredText(font, layout, title, gp);
        font.draw(gp.batch, title, x, gp.sSetting.screenHeight / 2);
        fadeBlack.draw();
    }

}
