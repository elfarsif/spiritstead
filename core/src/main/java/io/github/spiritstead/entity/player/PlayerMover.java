package io.github.spiritstead.entity.player;

import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.KeyHandler;

public class PlayerMover {
    Player player = Game.player;

    public void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            Game.player.direction = Game.player.direction.UP;
        } else if (keyH.downPressed) {
            Game.player.direction = Game.player.direction.DOWN;
        } else if (keyH.leftPressed) {
            Game.player.direction = Game.player.direction.LEFT;
        } else if (keyH.rightPressed) {
            Game.player.direction = Game.player.direction.RIGHT;
        }
    }

    public void move() {
        if (!Game.player.collisionOn) {
            switch (Game.player.direction) {
                case UP:
                    Game.player.getWorldPosition().setY(Game.player.getWorldPosition().getY() + Game.player.speed);
                    break;
                case DOWN:
                    Game.player.getWorldPosition().setY(Game.player.getWorldPosition().getY() - Game.player.speed);
                    break;
                case LEFT:
                    Game.player.getWorldPosition().setX(Game.player.getWorldPosition().getX() - Game.player.speed);
                    break;
                case RIGHT:
                    Game.player.getWorldPosition().setX(Game.player.getWorldPosition().getX() + Game.player.speed);
                    break;
            }
        }
    }

}
