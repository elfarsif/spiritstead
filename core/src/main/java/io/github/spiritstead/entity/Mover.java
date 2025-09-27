package io.github.spiritstead.entity;

import io.github.spiritstead.main.Game;
import jdk.internal.foreign.abi.Binding;

public class Mover {
    private Moveable moveable;

    public Mover(Moveable moveable) {
        this.moveable = moveable;
    }

    public void move() {
        if (!moveable.isCollisionOn()) {
            switch (moveable.getDirection()) {
                case UP:
                    moveable.getWorldPosition().setY(moveable.getWorldPosition().getY() + moveable.getSpeed());
                    break;
                case DOWN:
                    moveable.getWorldPosition().setY(moveable.getWorldPosition().getY() - moveable.getSpeed());
                    break;
                case LEFT:
                    moveable.getWorldPosition().setX(moveable.getWorldPosition().getX() - moveable.getSpeed());
                    break;
                case RIGHT:
                    moveable.getWorldPosition().setX(moveable.getWorldPosition().getX() + moveable.getSpeed());
                    break;
            }
        }
    }
}
