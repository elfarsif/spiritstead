package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class EntityCollision {
    GamePanel gp;

    public EntityCollision(GamePanel gp){
        this.gp = gp;
    }

    public int check(Entity entity, Entity[] target){


        int index = 9999;

        for (int i = 0; i<target.length;i++){
            if (target[i]!=null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX+ entity.solidArea.x;
                entity.solidArea.y = entity.worldY +entity.solidArea.y;
                //get objects solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction){
                    case UP:
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn=true;
                            index = i;
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn=true;
                            index = i;
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn=true;
                            index = i;
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn=true;
                            index = i;
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;

    }
}
