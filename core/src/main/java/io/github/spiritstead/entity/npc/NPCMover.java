package io.github.spiritstead.entity.npc;

public class NPCMover {
    NPC npc;

    public NPCMover(NPC npc) {
        this.npc = npc;
    }

    public void move() {
        if (!npc.isCollisionOn()) {
            switch (npc.getValues().getDirection()) {
                case UP:
                    npc.getValues().getWorldPosition().setY(npc.getValues().getWorldPosition().getY() + npc.getValues().getSpeed());
                    break;
                case DOWN:
                    npc.getValues().getWorldPosition().setY(npc.getValues().getWorldPosition().getY() - npc.getValues().getSpeed());
                    break;
                case LEFT:
                    npc.getValues().getWorldPosition().setX(npc.getValues().getWorldPosition().getX() - npc.getValues().getSpeed());
                    break;
                case RIGHT:
                    npc.getValues().getWorldPosition().setX(npc.getValues().getWorldPosition().getX() + npc.getValues().getSpeed());
                    break;
            }
        }
    }

}
