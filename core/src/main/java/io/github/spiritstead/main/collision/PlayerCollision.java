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
        gp.playScreen.player.solidArea.x = gp.playScreen.player.worldX + gp.playScreen.player.solidArea.x;
        gp.playScreen.player.solidArea.y = gp.playScreen.player.worldY + gp.playScreen.player.solidArea.y;
    }

    private void checkUpCollision() {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gp.playScreen.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkDownCollision() {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gp.playScreen.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkLeftCollision() {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gp.playScreen.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void checkRightCollision() {
        entity.solidArea.x += entity.speed;
        if (entity.solidArea.intersects(gp.playScreen.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void restoreDefaultSolidAreaValues() {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.playScreen.player.solidArea.x = gp.playScreen.player.solidAreaDefaultX;
        gp.playScreen.player.solidArea.y = gp.playScreen.player.solidAreaDefaultY;
    }
}
