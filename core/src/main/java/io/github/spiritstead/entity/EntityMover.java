package io.github.spiritstead.entity;

import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.KeyHandler;

public class EntityMover {
    Entity entity;

    public EntityMover(Entity entity) {
        this.entity = entity;
    }

    public void assignKeyPressToDirection(KeyHandler keyH) {
        if (keyH.upPressed) {
            entity.direction = entity.direction.UP;
        } else if (keyH.downPressed) {
            entity.direction = entity.direction.DOWN;
        } else if (keyH.leftPressed) {
            entity.direction = entity.direction.LEFT;
        } else if (keyH.rightPressed) {
            entity.direction = entity.direction.RIGHT;
        }
    }

    public void move() {
        if (!entity.collisionOn) {
            switch (entity.direction) {
                case UP:
                    entity.worldY += entity.speed;
                    break;
                case DOWN:
                    entity.worldY -= entity.speed;
                    break;
                case LEFT:
                    entity.worldX -= entity.speed;
                    break;
                case RIGHT:
                    entity.worldX += entity.speed;
                    break;
            }
        }
    }

}
