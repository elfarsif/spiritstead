package io.github.spiritstead.entity;

public final class Npcs {
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

    public int size() { return this.npcs.length; }
    public NPC get(int i) { return this.npcs[i]; }
    public void interact(int i) { this.npcs[0].interact(); }
}
