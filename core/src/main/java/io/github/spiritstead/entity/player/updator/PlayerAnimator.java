package io.github.spiritstead.entity.player.updator;

import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.FrameGate;

public class PlayerAnimator {
    Player player;
    FrameGate frameGate;

    public PlayerAnimator(Player player) {
        this.player = player;
        this.frameGate = new FrameGate(15);
    }

    public void update() {
        if (frameGate.tick()) {
            if (player.spriteNum == 1) {
                player.spriteNum = 2;
            } else if (player.spriteNum == 2) {
                player.spriteNum = 1;
            }
            frameGate.reset();
        }
    }

}
