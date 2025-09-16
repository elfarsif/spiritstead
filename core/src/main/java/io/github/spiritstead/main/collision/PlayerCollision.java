package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class PlayerCollision {
    GamePanel gp;
    Entity entity;

    public PlayerCollision(GamePanel gp) {
        this.gp = gp;
    }

    public void check(Entity entity) {
        this.entity = entity;
        intializeEntitySolidArea();
        initializePlayerSolidArea();
        checkCollisionForAllDirections(entity);
        restoreDefaultSolidAreaValues();

    }

    private void checkCollisionForAllDirections(Entity entity) {
        switch (entity.direction) {
            case UP:
                checkUpCollision();
                break;
            case DOWN:
                checkDownCollision();
                break;
            case LEFT:
                checkLeftCollision();
                break;
            case RIGHT:
                checkRightCollision();
                break;
        }
    }

    private void intializeEntitySolidArea() {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
    }

    private void initializePlayerSolidArea() {
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
    }

    private void checkUpCollision() {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkDownCollision() {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkLeftCollision() {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkRightCollision() {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void restoreDefaultSolidAreaValues() {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
