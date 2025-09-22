package io.github.spiritstead.entity;

import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

public class EntityMover {
    PlayerInteractable entity;

    public EntityMover(PlayerInteractable entity) {
        this.entity = entity;
    }

    public void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            entity.setDirection(Direction.UP);
        } else if (keyH.downPressed) {
            entity.setDirection(Direction.DOWN);
        } else if (keyH.leftPressed) {
            entity.setDirection(Direction.LEFT);
        } else if (keyH.rightPressed) {
            entity.setDirection(Direction.RIGHT);
        }
    }

    public void move() {
        if (!entity.isCollisionOn()) {
            switch (entity.getDirection()) {
                case UP:
                    entity.setWorldY(entity.getWorldY() + entity.getSpeed());
                    break;
                case DOWN:
                    entity.setWorldY(entity.getWorldY() - entity.getSpeed());
                    break;
                case LEFT:
                    entity.setWorldX(entity.getWorldX() - entity.getSpeed());
                    break;
                case RIGHT:
                    entity.setWorldX(entity.getWorldX() + entity.getSpeed());
                    break;
            }
        }
    }

}
