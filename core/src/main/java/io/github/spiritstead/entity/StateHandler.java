package io.github.spiritstead.entity;

public class StateHandler {
    private Enum currentState;
    private Enum defaultState;

    public StateHandler(Enum currentState) {
        this.currentState = currentState;
        this.defaultState = currentState;
    }

    public void setCurrentState(Enum currentState) { this.currentState = currentState; }
    public boolean isState(Enum state) { return this.currentState == state; }
    public Enum getDefaultState() { return this.defaultState; }
}
