package io.github.spiritstead.collision;

import io.github.spiritstead.entity.player.TileColliadable;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;
import io.github.spiritstead.tile.TileManager;

public class TileCollision implements Collision {
    GamePanel gp;
    int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
    int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
    int tileNum1, tileNum2;
    TileManager tileM;
    private TileColliadable entity;

    public TileCollision(GamePanel gp, TileColliadable entity) {
        this.gp = gp;
        this.entity = entity;
    }

    public void check() {
        this.tileM = gp.system.tileM;
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
        entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;
        entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y;
    }
}
