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

        switch (entity.direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }

        restoreDefaultSolidAreaValues();

    }

    private void intializeEntitySolidArea() {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
    }

    private void initializePlayerSolidArea() {
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
    }

    private void moveUp() {
        entity.solidArea.y += entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void moveDown() {
        entity.solidArea.y -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void moveLeft() {
        entity.solidArea.x -= entity.speed;
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }
    }

    private void moveRight() {
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
