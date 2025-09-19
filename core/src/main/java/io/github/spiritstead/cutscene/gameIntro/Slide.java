package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public interface Slide {
    public void draw();

    public ArrayList<String> getTexts();

    public void setDisplayedText(String displayedText);

    public void setCharIndex(int charIndex);

    public void setCombinedText(String combinedText);

    public int getTextCounter();

    public void setTextCounter(int textCounter);
}
