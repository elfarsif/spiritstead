package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class EntityCollision {
    GamePanel gp;
    int index;
    Entity entity;

    public EntityCollision(GamePanel gp) {
        this.gp = gp;
    }

    public int check(Entity entity, Entity[] targets) {
        index = 9999;
        this.entity = entity;

        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) {
                intializeEntitySolidArea();
                initializeTargetEntitySolidArea(targets[i]);
                checkCollisionForAllDirections(entity, targets, i);
                restoreDefaultAreaValues(entity, targets[i]);
            }
        }
        return index;
    }

    private void intializeEntitySolidArea() {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
    }

    private void initializeTargetEntitySolidArea(Entity target) {
        target.solidArea.x = target.worldX + target.solidArea.x;
        target.solidArea.y = target.worldY + target.solidArea.y;
    }

    private void checkCollisionForAllDirections(Entity entity, Entity[] target, int i) {
        switch (entity.direction) {
            case UP:
                checkUpCollision(entity, target, i);
                break;
            case DOWN:
                checkDownCollision(entity, target, i);
                break;
            case LEFT:
                checkLeftCollision(entity, target, i);
                break;
            case RIGHT:
                checkRightCollision(entity, target, i);
                break;
        }
    }

    private void restoreDefaultAreaValues(Entity entity, Entity target) {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        target.solidArea.x = target.solidAreaDefaultX;
        target.solidArea.y = target.solidAreaDefaultY;
    }

    private void checkRightCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
    }

    private void checkLeftCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
    }

    private void checkDownCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
    }

    private void checkUpCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
    }
}
