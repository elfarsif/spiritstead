package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.GameObject;

public class ObjectCollision implements Collision {
    private GamePanel gp;
    private Entity entity;

    private int index;

    private GameObject gameObject;

    public ObjectCollision(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;
    }

    public void check() {
        this.index = 9999;
        boolean player = entity instanceof Player;

        for (int i = 0; i < this.gp.system.aSetter.objects.length; i++) {
            if (this.gp.system.aSetter.objects[i] != null) {
                this.gameObject = this.gp.system.aSetter.objects[i];
                intializeEntitySolidArea();
                initializeObjectSolidArea(i);
                checkCollisionForAllDirections(entity, player, i);
                restoreDefaultAreaValues(entity, i);
            }
        }
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
        gameObject.solidArea.x = gameObject.solidAreaDefaultX;
        gameObject.solidArea.y = gameObject.solidAreaDefaultY;
    }

    private void checkRightCollision(Entity entity, boolean player, int i) {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkLeftCollision(Entity entity, boolean player, int i) {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkDownCollision(Entity entity, boolean player, int i) {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkUpCollision(Entity entity, boolean player, int i) {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
    }

    private void initializeObjectSolidArea(int i) {
        gameObject.solidArea.x = gameObject.worldX + gameObject.solidArea.x;
        gameObject.solidArea.y = gameObject.worldY + gameObject.solidArea.y;
    }

    private void intializeEntitySolidArea() {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
    }

    public int getIndex() {
        return index;
    }

}
