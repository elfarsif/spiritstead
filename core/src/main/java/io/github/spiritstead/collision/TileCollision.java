package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tile.TileManager;

public class TileCollision {
    GamePanel gp;
    int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
    int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
    int tileNum1, tileNum2;
    TileManager tileM;

    public TileCollision(GamePanel gp) {
        this.gp = gp;

    }

    public void check(Entity entity) {
        this.tileM = gp.system.tileM;
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
        entityRightCol = (entityRightWorldX + entity.speed) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkLeftCollision(Entity entity) {
        entityLeftCol = (entityLeftWorldX - entity.speed) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkDownCollision(Entity entity) {
        entityBottomRow = (entityBottomWorldY - entity.speed) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void checkUpCollision(Entity entity) {
        entityTopRow = (entityTopWorldY + entity.speed) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    private void intializeLinesForCollisionDetection() {
        entityLeftCol = entityLeftWorldX / ScreenSetting.TILE_SIZE;
        entityRightCol = entityRightWorldX / ScreenSetting.TILE_SIZE;
        entityTopRow = entityTopWorldY / ScreenSetting.TILE_SIZE;
        entityBottomRow = entityBottomWorldY / ScreenSetting.TILE_SIZE;
    }

    private void initializeEntitySolidArea(Entity entity) {
        entityLeftWorldX = entity.worldX + entity.solidArea.x;
        entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        entityTopWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        entityBottomWorldY = entity.worldY + entity.solidArea.y;
    }
}
