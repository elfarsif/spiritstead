package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class EntityCollision implements Collision {
    private int index;

    private Entity entity;
    private Entity[] targets;

    public EntityCollision(Entity entity, Entity[] targets) {
        this.entity = entity;
        this.targets = targets;
    }

    public void check() {
        this.index = 9999;

        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) {
                intializeEntitySolidArea();
                initializeTargetEntitySolidArea(targets[i]);
                checkCollisionForAllDirections(entity, targets, i);
                restoreDefaultAreaValues(entity, targets[i]);
            }
        }
    }

    private void intializeEntitySolidArea() {
        this.entity.solidArea.x = this.entity.worldX + this.entity.solidArea.x;
        this.entity.solidArea.y = this.entity.worldY + this.entity.solidArea.y;
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
            this.index = i;
        }
    }

    private void checkLeftCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            this.index = i;
        }
    }

    private void checkDownCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            this.index = i;
        }
    }

    private void checkUpCollision(Entity entity, Entity[] target, int i) {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(target[i].solidArea)) {
            entity.collisionOn = true;
            this.index = i;
        }
    }

    public int getIndex() {
        return index;
    }
}
