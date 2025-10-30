package io.github.spiritstead.entity;

import io.github.spiritstead.tools.FrameGate;

public final class Action {
    private final Direction.Holder direction;
    private final FrameGate frameGate;

    private Action(Direction.Holder direction, FrameGate frameGate) {
        this.direction = direction;
        this.frameGate = frameGate;
    }

    public static Action move(Direction direction, int duration) {
        return new Action(new Direction.Holder(direction), new FrameGate(duration));
    }

    public Direction update() {
        if (this.frameGate.tick()) {
            this.direction.set(Direction.UP);
            this.frameGate.reset();
        }
        return this.direction.get();
    }
}
