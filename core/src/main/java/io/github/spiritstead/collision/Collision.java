package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.Moveable;

public final class Collision {
    private boolean isColliding = false;
    private Moveable moveable;
    private Collidable collidable;

    public boolean check(Moveable moveable, Collidable collidable) {
        this.isColliding = false;
        this.moveable = moveable;
        this.collidable = collidable;
        this.intializeMoveableSolidAreaInWorld();
        this.initializeCollidableSolidAreaInWorld();
        this.checkSolidAreaCollisionForAllDirections();
        this.restoreDefaultAreaValues();
        return this.isColliding;
    }

    private void intializeMoveableSolidAreaInWorld() {
        this.moveable.getSolidArea().getRect().x = this.moveable.getWorldPosition().getX() + this.moveable.getSolidArea().getRect().x;
        this.moveable.getSolidArea().getRect().y = this.moveable.getWorldPosition().getY() + this.moveable.getSolidArea().getRect().y;
    }

    private void initializeCollidableSolidAreaInWorld() {
        this.collidable.getSolidArea().getRect().x = this.collidable.getWorldPosition().getX() + this.collidable.getSolidArea().getRect().x;
        this.collidable.getSolidArea().getRect().y = this.collidable.getWorldPosition().getY() + this.collidable.getSolidArea().getRect().y;
    }

    private void checkSolidAreaCollisionForAllDirections() {
        switch (this.moveable.getDirection()) {
            case UP:
                this.moveable.getSolidArea().getRect().y += this.moveable.getSpeed();
                checkCollision();
                break;
            case DOWN:
                this.moveable.getSolidArea().getRect().y -= this.moveable.getSpeed();
                checkCollision();
                break;
            case LEFT:
                this.moveable.getSolidArea().getRect().x -= this.moveable.getSpeed();
                checkCollision();
                break;
            case RIGHT:
                this.moveable.getSolidArea().getRect().x += this.moveable.getSpeed();
                checkCollision();
                break;
        }
    }

    private void checkCollision() {
        if (this.moveable.getSolidArea().getRect().intersects(this.collidable.getSolidArea().getRect())) {
            this.moveable.setCollisionOn(true);
            this.isColliding = true;
        }
    }

    private void restoreDefaultAreaValues() {
        this.moveable.getSolidArea().getRect().x = this.moveable.getSolidArea().getDefaultX();
        this.moveable.getSolidArea().getRect().y = this.moveable.getSolidArea().getDefaultY();
        this.collidable.getSolidArea().getRect().x = this.collidable.getSolidArea().getDefaultX();
        this.collidable.getSolidArea().getRect().y = this.collidable.getSolidArea().getDefaultY();
    }
}
