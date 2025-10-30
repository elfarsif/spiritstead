package io.github.spiritstead.main;

import io.github.spiritstead.tools.BlackAlphaFrames;
import io.github.spiritstead.tools.FrameGate;

public final class DayCycle {
    private final FrameGate fadeFrameGate;
    private final BlackAlphaFrames blackAlphaFrames;
    private int frameCounter = 0;

    public DayCycle(FrameGate fadeFrameGate, BlackAlphaFrames blackAlphaFrames) {
        this.fadeFrameGate = fadeFrameGate;
        this.blackAlphaFrames = blackAlphaFrames;
        this.frameCounter = this.blackAlphaFrames.frames.size() - 1;
    }

    public void draw() {
        if (this.fadeFrameGate.tick() && this.frameCounter > 3) {
            this.frameCounter--;
            this.fadeFrameGate.reset();
        }

        Game.batch.draw(this.blackAlphaFrames.frames.get(this.frameCounter), 0, 0);
    }
}
