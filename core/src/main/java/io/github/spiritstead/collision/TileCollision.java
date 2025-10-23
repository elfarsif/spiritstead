package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tile.TileManager;

public class TileCollision implements CollisionType {
    private final TileManager tileM;
    private final Collidable entity;

    private int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
    private int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
    private int tileNum1, tileNum2;
    private boolean isCollinding;

    public TileCollision(TileManager tileM, Collidable entity) {
        this.tileM = tileM;
        this.entity = entity;
    }

    public boolean check() {
        this.isCollinding = false;
        initializeEntitySolidArea();
        intializeLinesForCollisionDetection();
        switch (entity.getDirection()) {
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

        return this.isCollinding;
    }

    private void checkRightCollision() {
        entityRightCol = (entityRightWorldX + entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            this.isCollinding = true;
        }
    }

    private void checkLeftCollision() {
        entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            this.isCollinding = true;
        }
    }

    private void checkDownCollision() {
        entityBottomRow = (entityBottomWorldY - entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            this.isCollinding = true;
        }
    }

    private void checkUpCollision() {
        entityTopRow = (entityTopWorldY + entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            this.isCollinding = true;
        }
    }

    private void intializeLinesForCollisionDetection() {
        entityLeftCol = entityLeftWorldX / ScreenSetting.TILE_SIZE;
        entityRightCol = entityRightWorldX / ScreenSetting.TILE_SIZE;
        entityTopRow = entityTopWorldY / ScreenSetting.TILE_SIZE;
        entityBottomRow = entityBottomWorldY / ScreenSetting.TILE_SIZE;
    }

    private void initializeEntitySolidArea() {
        entityLeftWorldX = entity.getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entityRightWorldX = entity.getWorldPosition().getX() + entity.getSolidArea().getRect().x + entity.getSolidArea().getRect().width;
        entityTopWorldY = entity.getWorldPosition().getY() + entity.getSolidArea().getRect().y + entity.getSolidArea().getRect().height;
        entityBottomWorldY = entity.getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }
}
