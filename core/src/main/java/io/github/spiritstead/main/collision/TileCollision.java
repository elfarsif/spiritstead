package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class TileCollision {
    GamePanel gp;

    public TileCollision(GamePanel gp){
        this.gp = gp;

    }
    public void check(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        int entityBottomWorldY  = entity.worldY + entity.solidArea.y;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        //just need to check 2 tiles in each direction
        int tileNum1,tileNum2;

        switch (entity.direction){
            case UP:
                entityTopRow = (entityTopWorldY + entity.speed)/gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
        }

    }
}
