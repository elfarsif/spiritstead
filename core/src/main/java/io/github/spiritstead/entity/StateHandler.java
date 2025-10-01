package io.github.spiritstead.entity;

public class StateHandler {
    private Enum currentState;

    public StateHandler(Enum currentState) {
        this.currentState = currentState;
    }

    public Enum getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Enum currentState) {
        this.currentState = currentState;
    }

    public boolean isState(Enum state) {
        return this.currentState == state;
    }

}
