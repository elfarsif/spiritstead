package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.Player;
import io.github.spiritstead.main.GamePanel;

public class ObjectCollision {
    GamePanel gp;

    public ObjectCollision(GamePanel gp){
        this.gp = gp;
    }

    public int check(Entity entity, boolean player){

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
}
