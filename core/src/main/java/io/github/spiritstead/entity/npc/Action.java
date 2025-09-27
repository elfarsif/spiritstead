package io.github.spiritstead.entity.npc;

import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.FrameGate;

import java.util.Random;

public class Action {
    NPC npc;
    FrameGate frameGate;

    public Action(NPC npc) {
        this.npc = npc;
        this.frameGate = new FrameGate(120);
    }

    public void moveUp(Direction direction, int waitFrames) {
        frameGate.setWaitFrames(waitFrames);
        if (frameGate.tick()) {
            npc.getValues().direction = direction;
            frameGate.reset();
        }
    }

}
