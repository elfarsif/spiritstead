package io.github.spiritstead.dialogue;

public class Dialogue {
    public enum Phase {STARTING, CHOOSING, CHOOSINGEFFECT, ADVANCING, ENDING}

    public Phase phase;

    public Dialogue() {
        this.phase = Phase.STARTING;
    }
}
