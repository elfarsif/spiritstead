package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

/**
 * Generates an arraylist of black frames of decrease opacity
 */
public class BlackAlphaFrames {
    private Pixmap pixmap;
    public ArrayList<Texture> frames = new ArrayList<>();

    public BlackAlphaFrames() {
        this.initializeFrames();
    }

    private void initializeFrames() {
        //cant increment floats
        int alphaCounter = 10;
        while (alphaCounter >= 0) {
            float alphaF = (float) alphaCounter / 10;
            generateBlackPixmap(alphaF);
            this.frames.add(new Texture(pixmap));
            alphaCounter -= 2;
        }

    }

    private void generateBlackPixmap(float alpha) {
        pixmap = new Pixmap(ScreenSetting.SCREEN_WIDTH, ScreenSetting.SCREEN_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, alpha);
        pixmap.fill();
    }

}
