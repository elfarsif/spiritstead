package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.main.Game;

public class Updator {
    private PlayerMover playerMover;
    private PlayerAnimator playerAnimator;
    private PlayerCollisionHandler playerCollisionHandler;

    public Updator(Player player, NPC[] npcs) {
        this.playerMover = new PlayerMover();
        this.playerAnimator = new PlayerAnimator(player);
        this.playerCollisionHandler = new PlayerCollisionHandler(player, npcs);

    }

    public void update() {
        if (Game.keyH.upPressed || Game.keyH.downPressed || Game.keyH.leftPressed || Game.keyH.rightPressed) {
            playerMover.assignKeyPressToDirection(Game.keyH);
            playerCollisionHandler.checkAll();
            playerMover.move();
            playerAnimator.update();
        }
    }
}
