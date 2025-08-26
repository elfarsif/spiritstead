package io.github.spiritstead.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Entity;

import static io.github.spiritstead.entity.Direction.UP;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkEntityIsCollidingWithCollidableTile(Entity entity){

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

    public int checkEntityIsCollidingWithObject(Entity entity, boolean player){

        int index = 9999;

        for (int i = 0; i<gp.objects.length;i++){
            if (gp.objects[i]!=null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX+ entity.solidArea.x;
                entity.solidArea.y = entity.worldY +entity.solidArea.y;
                //get objects solid area position
                gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidArea.x;
                gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidArea.y;

                switch (entity.direction){
                    case UP:
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                           if (gp.objects[i].collision == true){
                               entity.collisionOn=true;
                           }
                           if (player){
                               index = i;
                           }
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision == true){
                                entity.collisionOn=true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision == true){
                                entity.collisionOn=true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objects[i].solidArea)){
                            if (gp.objects[i].collision == true){
                                entity.collisionOn=true;
                            }
                            if (player){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
                gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public void draw(SpriteBatch batch){
        //TODO draw collision here
    }
}
