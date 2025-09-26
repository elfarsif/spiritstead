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
            switch (moveable.getValues().direction) {
                case UP:
                    moveable.getValues().getWorldPosition().setY(moveable.getValues().getWorldPosition().getY() + moveable.getValues().speed);
                    break;
                case DOWN:
                    moveable.getValues().getWorldPosition().setY(moveable.getValues().getWorldPosition().getY() - moveable.getValues().speed);
                    break;
                case LEFT:
                    moveable.getValues().getWorldPosition().setX(moveable.getValues().getWorldPosition().getX() - moveable.getValues().speed);
                    break;
                case RIGHT:
                    moveable.getValues().getWorldPosition().setX(moveable.getValues().getWorldPosition().getX() + moveable.getValues().speed);
                    break;
            }
        }
    }
}
