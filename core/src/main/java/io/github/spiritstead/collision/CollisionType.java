package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.tile.TileManager;

public interface CollisionType {
    public boolean check(TileManager tileM, Collidable entity);
}
