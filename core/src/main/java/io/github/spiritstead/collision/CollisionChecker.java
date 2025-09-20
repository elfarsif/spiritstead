package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.main.GamePanel;

public class CollisionChecker {
    private GamePanel gp;
    private TileCollision tileCollision;
    private ObjectCollision objectCollision;
    private EntityCollision entityCollision;
    private PlayerCollision playerCollision;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
        this.tileCollision = new TileCollision(gp);
        this.objectCollision = new ObjectCollision(gp);
        this.entityCollision = new EntityCollision(gp);
        this.playerCollision = new PlayerCollision(gp);
    }

    public void checkEntityIsCollidingWithCollidableTile(Entity entity) {
        this.tileCollision.check(entity);
    }

    public int checkEntityIsCollidingWithObject(Entity entity, boolean player) {
        return this.objectCollision.check(entity, player);
    }

    public int checkPlayerIsCollidingWithEntity(Entity entity, Entity[] target) {
        return this.entityCollision.check(entity, target);
    }

    public void checkEntityIsCollidingWithPlayer(Entity entity) {
        this.playerCollision.check(entity);
    }

}
