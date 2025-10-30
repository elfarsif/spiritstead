package io.github.spiritstead.entity;

import jdk.internal.foreign.abi.Binding;

public final class Mover {

    public void move(Moveable moveable) {
        if (! moveable.isCollisionOn()) {
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
