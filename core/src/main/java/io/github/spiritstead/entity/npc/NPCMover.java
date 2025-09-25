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
                    npc.getWorldPosition().setY(npc.getWorldPosition().getY() + npc.getSpeed());
                    break;
                case DOWN:
                    npc.getWorldPosition().setY(npc.getWorldPosition().getY() - npc.getSpeed());
                    break;
                case LEFT:
                    npc.getWorldPosition().setX(npc.getWorldPosition().getX() - npc.getSpeed());
                    break;
                case RIGHT:
                    npc.getWorldPosition().setX(npc.getWorldPosition().getX() + npc.getSpeed());
                    break;
            }
        }
    }

}
