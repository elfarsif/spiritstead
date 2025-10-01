package io.github.spiritstead.tools;

/**
 * This class should be used to manage effects that are required to happen over a period of time slower
 * than the update method is called. ex
 */
public class FrameGate {
    int counter = 0;
    int waitFrames;

    public FrameGate(int waitFrames) {
        if (waitFrames <= 0) throw new IllegalArgumentException("waitframes must be >0");
        this.waitFrames = waitFrames;
    }

    public boolean tick() {
        counter++;
        if (counter == waitFrames) {
            return true;
        }
        return false;
    }

    public void reset() {
        counter = 0;
    }

    public void setWaitFrames(int waitFrames) {
        this.waitFrames = waitFrames;
    }
}
