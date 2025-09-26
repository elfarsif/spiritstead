package io.github.spiritstead.collision;

import io.github.spiritstead.entity.TileColliadable;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tile.TileManager;

public class TileCollision implements Collision {
    int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
    int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
    int tileNum1, tileNum2;
    TileManager tileM;
    private TileColliadable entity;

    public TileCollision(TileManager tileM, TileColliadable entity) {
        this.tileM = tileM;
        this.entity = entity;
    }

    public void check() {
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

    }

    private void checkRightCollision() {
        entityRightCol = (entityRightWorldX + entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisonOn(true);
        }
    }

    private void checkLeftCollision() {
        entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityLeftCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisonOn(true);
        }
    }

    private void checkDownCollision() {
        entityBottomRow = (entityBottomWorldY - entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisonOn(true);
        }
    }

    private void checkUpCollision() {
        entityTopRow = (entityTopWorldY + entity.getSpeed()) / ScreenSetting.TILE_SIZE;

        tileNum1 = tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = tileM.mapTileNum[entityRightCol][entityTopRow];

        if (tileM.tile[tileNum1].collision || tileM.tile[tileNum2].collision) {
            entity.setCollisonOn(true);
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
