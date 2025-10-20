package io.github.spiritstead.entity;

public class Npcs {
    private final NPC[] npcs;

    public Npcs(NPC[] npcs) { this.npcs = npcs; }

    public void draw() {
        for (NPC npc : this.npcs) {
            if (npc != null) {
                npc.draw();
            }
        }
    }

    public void update() {
        for (NPC npc : this.npcs) {
            if (npc != null) {
                npc.update();
            }
        }
    }

}
