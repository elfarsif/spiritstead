package io.github.spiritstead.entity.player.updator;

import io.github.spiritstead.entity.Mover;
import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.KeyHandler;

public class Updator {
    private PlayerAnimator playerAnimator;
    private PlayerCollisionHandler playerCollisionHandler;
    private Mover mover;

    public Updator(Player player, NPC[] npcs) {
        this.playerAnimator = new PlayerAnimator(player);
        this.playerCollisionHandler = new PlayerCollisionHandler(player, npcs);
        this.mover = new Mover(player);
    }

    public void update() {
        if (Game.keyH.upPressed || Game.keyH.downPressed || Game.keyH.leftPressed || Game.keyH.rightPressed) {
            assignKeyPressToDirection(Game.keyH);
            playerCollisionHandler.checkAll();
            mover.move();
            playerAnimator.update();
        }
        playerCollisionHandler.checkNPCCollision();
    }

    public void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            Game.player.getValues().direction = Game.player.getValues().direction.UP;
        } else if (keyH.downPressed) {
            Game.player.getValues().direction = Game.player.getValues().direction.DOWN;
        } else if (keyH.leftPressed) {
            Game.player.getValues().direction = Game.player.getValues().direction.LEFT;
        } else if (keyH.rightPressed) {
            Game.player.getValues().direction = Game.player.getValues().direction.RIGHT;
        }
    }
}
