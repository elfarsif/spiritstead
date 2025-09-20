package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.FrameGate;

public class PlayerAnimator {
    Entity entity;
    FrameGate frameGate;

    public PlayerAnimator(Entity entity) {
        this.entity = entity;
        this.frameGate = new FrameGate(15);
    }

    public void update() {
        if (frameGate.tick()) {
            if (entity.spriteNum == 1) {
                entity.spriteNum = 2;
            } else if (entity.spriteNum == 2) {
                entity.spriteNum = 1;
            }
            frameGate.reset();
        }
    }

}
