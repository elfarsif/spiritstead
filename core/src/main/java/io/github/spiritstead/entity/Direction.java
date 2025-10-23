package io.github.spiritstead.entity;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ANY;

    public final static class Holder {
        private Direction value;

        public Holder(Direction initial) {
            this.value = initial;
        }

        public Direction get() { return this.value; }

        public void set(Direction direction) { this.value = direction; }
    }
}
