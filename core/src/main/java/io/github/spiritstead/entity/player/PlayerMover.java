package io.github.spiritstead.entity.player;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.KeyHandler;

public class PlayerMover {
    Player player;

    public PlayerMover(Player player) {
        this.player = player;
    }

    public void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            player.direction = player.direction.UP;
        } else if (keyH.downPressed) {
            player.direction = player.direction.DOWN;
        } else if (keyH.leftPressed) {
            player.direction = player.direction.LEFT;
        } else if (keyH.rightPressed) {
            player.direction = player.direction.RIGHT;
        }
    }

    public void move() {
        if (!player.collisionOn) {
            switch (player.direction) {
                case UP:
                    player.worldY += player.speed;
                    break;
                case DOWN:
                    player.worldY -= player.speed;
                    break;
                case LEFT:
                    player.worldX -= player.speed;
                    break;
                case RIGHT:
                    player.worldX += player.speed;
                    break;
            }
        }
    }

}
