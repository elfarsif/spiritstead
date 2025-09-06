package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ui.UIUtilities;

import java.util.ArrayList;

public interface Slide{
    public void draw(SpriteBatch batch);

    public ArrayList<String> getTexts();

    public void setDisplayedText(String displayedText);

    public void setCharIndex(int charIndex);

    public void setCombinedText(String combinedText);

    public int getTextCounter();

    public void setTextCounter(int textCounter);
}
