package io.github.spiritstead.cutscene;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

public class FadeBlack {
    GamePanel gp;
    ScreenSetting screenSetting;
    Pixmap pixmap;
    int fadeCounter = 0;
    int frameCounter = 0;
    int transitionSpeed = 10;
    ArrayList<Texture> frames = new ArrayList<>();

    public FadeBlack(GamePanel gp) {
        this.gp = gp;
        this.screenSetting = gp.sSetting;

        initializeFrames();

    }

    private void initializeFrames() {
        //cant increment floats
        int alphaCounter = 10;
        while (alphaCounter >= 0) {
            float alphaF = (float) alphaCounter / 10;
            generateBlackPixmap(alphaF);
            frames.add(new Texture(pixmap));
            alphaCounter -= 2;
        }

    }

    public void draw() {
        fadeCounter++;
        if (fadeCounter == transitionSpeed && frameCounter < frames.size() - 1) {
            frameCounter++;
            fadeCounter = 0;
        }

        gp.batch.draw(frames.get(frameCounter), 0, 0);
    }

    private void generateBlackPixmap(float alpha) {
        pixmap = new Pixmap(screenSetting.screenWidth, screenSetting.screenHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, alpha);
        pixmap.fill();
    }

    public void dispose() {
        pixmap.dispose();
    }
}
