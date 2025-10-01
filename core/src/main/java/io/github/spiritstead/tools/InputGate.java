package io.github.spiritstead.tools;

public class InputGate {
    private boolean isOpen = true;

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        this.isOpen = true;
    }

    public void close() {
        this.isOpen = false;
    }
}
