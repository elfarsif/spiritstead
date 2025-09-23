package io.github.spiritstead.entity.npc;

public class NPCMover {
    NPC npc;

    public NPCMover(NPC npc) {
        this.npc = npc;
    }

    public void move() {
        if (!npc.isCollisionOn()) {
            switch (npc.getDirection()) {
                case UP:
                    npc.setWorldY(npc.getWorldY() + npc.getSpeed());
                    break;
                case DOWN:
                    npc.setWorldY(npc.getWorldY() - npc.getSpeed());
                    break;
                case LEFT:
                    npc.setWorldX(npc.getWorldX() - npc.getSpeed());
                    break;
                case RIGHT:
                    npc.setWorldX(npc.getWorldX() + npc.getSpeed());
                    break;
            }
        }
    }

}
