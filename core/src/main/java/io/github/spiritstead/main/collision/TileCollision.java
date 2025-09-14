package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class TileCollision {
    GamePanel gp;

    int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;

    int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;

    int tileNum1, tileNum2;

    public TileCollision(GamePanel gp) {
        this.gp = gp;

    }

    public void check(Entity entity) {
        initializeEntitySolidArea(entity);
        intializeLinesForCollisionDetection();
        switch (entity.direction) {
            case UP:
                checkUpCollision(entity);
                break;
            case DOWN:
                checkDownCollision(entity);
                break;
            case LEFT:
                checkLeftCollision(entity);
                break;
            case RIGHT:
                checkRightCollision(entity);
                break;
        }

    }

    private void checkRightCollision(Entity entity) {
        entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkLeftCollision(Entity entity) {
        entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkDownCollision(Entity entity) {
        entityBottomRow = (entityBottomWorldY - entity.speed) / gp.tileSize;

        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkUpCollision(Entity entity) {
        entityTopRow = (entityTopWorldY + entity.speed) / gp.tileSize;

        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void intializeLinesForCollisionDetection() {
        entityLeftCol = entityLeftWorldX / gp.tileSize;
        entityRightCol = entityRightWorldX / gp.tileSize;
        entityTopRow = entityTopWorldY / gp.tileSize;
        entityBottomRow = entityBottomWorldY / gp.tileSize;
    }

    private void initializeEntitySolidArea(Entity entity) {
        entityLeftWorldX = entity.worldX + entity.solidArea.x;
        entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        entityTopWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        entityBottomWorldY = entity.worldY + entity.solidArea.y;
    }
}
