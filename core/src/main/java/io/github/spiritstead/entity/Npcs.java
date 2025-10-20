package io.github.spiritstead.entity;

public class Npcs {
    private final NPC[] npcs;

    public Npcs(NPC[] npcs) { this.npcs = npcs; }

    public void draw() {
        for (int i = 0; i < this.npcs.length; i++) {
            if (this.npcs[i] != null) {
                this.npcs[i].draw();
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.npcs.length; i++) {
            if (this.npcs[i] != null) {
                this.npcs[i].update();
            }
        }
    }

}
