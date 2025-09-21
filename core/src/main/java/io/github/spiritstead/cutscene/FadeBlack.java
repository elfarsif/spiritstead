package io.github.spiritstead.cutscene;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

public class FadeBlack {
    GamePanel gp;
    ScreenSetting screenSetting;
    Pixmap pixmap;
    int frameCounter = 0;
    private FrameGate fadeFrameGate;
    ArrayList<Texture> frames = new ArrayList<>();

    public FadeBlack(GamePanel gp) {
        this.gp = gp;
        this.screenSetting = gp.sSetting;
        this.fadeFrameGate = new FrameGate(10);
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
        if (fadeFrameGate.tick() && frameCounter < frames.size() - 1) {
            frameCounter++;
            fadeFrameGate.reset();
        }

        gp.batch.draw(frames.get(frameCounter), 0, 0);
    }

    private void generateBlackPixmap(float alpha) {
        pixmap = new Pixmap(screenSetting.SCREEN_WIDTH, screenSetting.SCREEN_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, alpha);
        pixmap.fill();
    }

    public void dispose() {
        pixmap.dispose();
    }
}
