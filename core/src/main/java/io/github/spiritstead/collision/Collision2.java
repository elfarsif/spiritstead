package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.Moveable;
import io.github.spiritstead.entity.npc.NPC;

import java.awt.*;

public class Collision2 {
    private boolean isColliding = false;
    private Moveable moveable;
    private Collidable collidable;

    public boolean check(Moveable moveable, Collidable collidable) {
        this.isColliding = false;
        this.moveable = moveable;
        this.collidable = collidable;
        this.intializeMoveableSolidAreaInWorld();
        this.initializeCollidableSolidAreaInWorld();
        this.checkCollisionForAllDirections();
        this.restoreDefaultAreaValues();
        return this.isColliding;
    }

    private void intializeMoveableSolidAreaInWorld() {
        this.moveable.getSolidArea().getRect().x = this.moveable.getValues().getWorldPosition().getX() + this.moveable.getSolidArea().getRect().x;
        this.moveable.getSolidArea().getRect().y = this.moveable.getValues().getWorldPosition().getY() + this.moveable.getSolidArea().getRect().y;
    }

    private void initializeCollidableSolidAreaInWorld() {
        this.collidable.getSolidArea().getRect().x = this.collidable.getValues().getWorldPosition().getX() + this.collidable.getSolidArea().getRect().x;
        this.collidable.getSolidArea().getRect().y = this.collidable.getValues().getWorldPosition().getY() + this.collidable.getSolidArea().getRect().y;
    }

    private void checkCollisionForAllDirections() {
        switch (this.moveable.getValues().direction) {
            case UP:
                this.moveable.getSolidArea().getRect().y += this.moveable.getValues().speed;
                checkCollision();
                break;
            case DOWN:
                this.moveable.getSolidArea().getRect().y -= this.moveable.getValues().speed;
                checkCollision();
                break;
            case LEFT:
                this.moveable.getSolidArea().getRect().x -= this.moveable.getValues().speed;
                checkCollision();
                break;
            case RIGHT:
                this.moveable.getSolidArea().getRect().x += this.moveable.getValues().speed;
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
