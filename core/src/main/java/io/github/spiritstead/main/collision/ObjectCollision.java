package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.GamePanel;

public class ObjectCollision {
    GamePanel gp;
    Entity entity;
    int index;

    public ObjectCollision(GamePanel gp) {
        this.gp = gp;
    }

    public int check(Entity entity, boolean player) {
        this.entity = entity;
        index = 9999;

        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i] != null) {
                intializeEntitySolidArea();
                initializeObjectSolidArea(i);
                checkCollisionForAllDirections(entity, player, i);
                restoreDefaultAreaValues(entity, i);
            }
        }

        return index;
    }

    private void checkCollisionForAllDirections(Entity entity, boolean player, int i) {
        switch (entity.direction) {
            case UP:
                checkUpCollision(entity, player, i);
                break;
            case DOWN:
                checkDownCollision(entity, player, i);
                break;
            case LEFT:
                checkLeftCollision(entity, player, i);
                break;
            case RIGHT:
                checkRightCollision(entity, player, i);
                break;
        }
    }

    private void restoreDefaultAreaValues(Entity entity, int i) {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
        gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;
    }

    private void checkRightCollision(Entity entity, boolean player, int i) {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
            if (gp.objects[i].collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkLeftCollision(Entity entity, boolean player, int i) {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
            if (gp.objects[i].collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkDownCollision(Entity entity, boolean player, int i) {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
            if (gp.objects[i].collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkUpCollision(Entity entity, boolean player, int i) {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
            if (gp.objects[i].collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void initializeObjectSolidArea(int i) {
        gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidArea.x;
        gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidArea.y;
    }

    private void intializeEntitySolidArea() {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
    }

}
