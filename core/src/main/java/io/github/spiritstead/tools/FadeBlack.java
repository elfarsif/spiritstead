package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FadeBlack {
    private SpriteBatch batch;
    private Pixmap pixmap;
    private int frameCounter = 0;
    private FrameGate fadeFrameGate;
    private BlackAlphaFrames blackAlphaFramFactory;

    public FadeBlack(SpriteBatch batch) {
        this.batch = batch;
        this.fadeFrameGate = new FrameGate(10);
        this.blackAlphaFramFactory = new BlackAlphaFrames();
    }

    public void draw() {
        if (fadeFrameGate.tick() && frameCounter < blackAlphaFramFactory.frames.size() - 1) {
            frameCounter++;
            fadeFrameGate.reset();
        }
        batch.draw(blackAlphaFramFactory.frames.get(frameCounter), 0, 0);
    }

    public void dispose() {
        pixmap.dispose();
    }
}
