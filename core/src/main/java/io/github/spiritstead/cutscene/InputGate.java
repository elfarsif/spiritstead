package io.github.spiritstead.cutscene;

public class InputGate {
    private boolean isOpen = false;

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
