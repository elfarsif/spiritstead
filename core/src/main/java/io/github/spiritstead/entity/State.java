package io.github.spiritstead.entity;

public final class State<T> {
    private T current;
    private T defaultState;

    public State(T defaultState) {
        this.current = defaultState;
        this.defaultState = defaultState;
    }

    public void setCurrent(T currentState) { this.current = currentState; }
    public boolean is(T state) { return this.current == state; }
    public T getCurrent() { return this.current; }
    public T getDefault() { return this.defaultState; }
}
