package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tile.TileManager;

public class TileCollision implements Collision {
    int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
    int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
    int tileNum1, tileNum2;
    TileManager tileM;
    private Collidable entity;

    public TileCollision(TileManager tileM, Collidable entity) {
        this.tileM = tileM;
        this.entity = entity;
    }

    public void check() {
        initializeEntitySolidArea();
        intializeLinesForCollisionDetection();
        switch (entity.getValues().getDirection()) {
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

    private void checkRightCollision() {
        entityRightCol = (entityRightWorldX + entity.getValues().getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisionOn(true);
        }
    }

    private void checkLeftCollision() {
        entityLeftCol = (entityLeftWorldX - entity.getValues().getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisionOn(true);
        }
    }

    private void checkDownCollision() {
        entityBottomRow = (entityBottomWorldY - entity.getValues().getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisionOn(true);
        }
    }

    private void checkUpCollision() {
        entityTopRow = (entityTopWorldY + entity.getValues().getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisionOn(true);
        }
    }

    private void intializeLinesForCollisionDetection() {
        entityLeftCol = entityLeftWorldX / ScreenSetting.TILE_SIZE;
        entityRightCol = entityRightWorldX / ScreenSetting.TILE_SIZE;
        entityTopRow = entityTopWorldY / ScreenSetting.TILE_SIZE;
        entityBottomRow = entityBottomWorldY / ScreenSetting.TILE_SIZE;
    }

    private void initializeEntitySolidArea() {
        entityLeftWorldX = entity.getValues().getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entityRightWorldX = entity.getValues().getWorldPosition().getX() + entity.getSolidArea().getRect().x + entity.getSolidArea().getRect().width;
        entityTopWorldY = entity.getValues().getWorldPosition().getY() + entity.getSolidArea().getRect().y + entity.getSolidArea().getRect().height;
        entityBottomWorldY = entity.getValues().getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }
}
