package io.github.spiritstead.main.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class CollisionChecker {
    GamePanel gp;
    TileCollision tileCollision;
    ObjectCollision objectCollision;
    EntityCollision entityCollision;
    PlayerCollision playerCollision;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
        tileCollision = new TileCollision(gp);
        objectCollision = new ObjectCollision(gp);
        entityCollision = new EntityCollision(gp);
        playerCollision = new PlayerCollision(gp);
    }

    public void checkEntityIsCollidingWithCollidableTile(Entity entity){
        tileCollision.check(entity);
    }

    public int checkEntityIsCollidingWithObject(Entity entity, boolean player){
        return objectCollision.check(entity,player);
    }

    public int checkPlayerIsCollidingWithEntity(Entity entity, Entity[] target){
        return entityCollision.check(entity,target);
    }

    public void checkEntityIsCollidingWithPlayer(Entity entity){
        playerCollision.check(entity);
    }

}
