package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ui.UIUtilities;

import java.util.ArrayList;

public class TitleSlide implements Slide {
    BitmapFont font;
    GamePanel gp;
    String title;
    GlyphLayout layout = new GlyphLayout();

    public TitleSlide(GamePanel gp, String title) {
        this.gp = gp;
        this.title = title;
        font = UIUtilities.initializeFont(font, "fonts/maruMonica.fnt");
    }

    @Override
    public void draw(SpriteBatch batch) {
        float x = UIUtilities.getXforCenteredText(font, layout, title, gp);
        font.draw(batch, title, x, gp.sSetting.screenHeight / 2);
    }

    @Override
    public ArrayList<String> getTexts() {
        return null;
    }

    @Override
    public void setDisplayedText(String displayedText) {

    }

    @Override
    public void setCharIndex(int charIndex) {

    }

    @Override
    public void setCombinedText(String combinedText) {

    }

    @Override
    public int getTextCounter() {
        return 0;
    }

    @Override
    public void setTextCounter(int textCounter) {

    }
}
