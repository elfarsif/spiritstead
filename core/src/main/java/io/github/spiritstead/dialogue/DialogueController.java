package io.github.spiritstead.dialogue;

public class DialogueController {
    public enum Phase {STARTING, CHOOSING, ADVANCING, ENDING}

    public Phase phase;

    public DialogueController() {
        this.phase = Phase.STARTING;
    }
}
