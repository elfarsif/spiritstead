package io.github.spiritstead.collision;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.Player;

public class NPCCollision implements Collision {
    private int index;

    private Player player;
    private NPC[] targets;

    public NPCCollision(Player player, NPC[] targets) {
        this.player = player;
        this.targets = targets;
    }

    public void check() {
        this.index = 9999;

        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) {
                intializeEntitySolidArea();
                initializeTargetEntitySolidArea(targets[i]);
                checkCollisionForAllDirections(targets, i);
                restoreDefaultAreaValues(targets[i]);
            }
        }
    }

    private void intializeEntitySolidArea() {
        this.player.solidArea.x = this.player.worldX + this.player.solidArea.x;
        this.player.solidArea.y = this.player.worldY + this.player.solidArea.y;
    }

    private void initializeTargetEntitySolidArea(NPC target) {
        target.getSolidArea().x = target.getWorldX() + target.getSolidArea().x;
        target.getSolidArea().y = target.getWorldY() + target.getSolidArea().y;
    }

    private void checkCollisionForAllDirections(NPC[] target, int i) {
        switch (player.direction) {
            case UP:
                player.solidArea.y += player.speed;
                checkCollision(target, i);
                break;
            case DOWN:
                player.solidArea.y -= player.speed;
                checkCollision(target, i);
                break;
            case LEFT:
                player.solidArea.x -= player.speed;
                checkCollision(target, i);
                break;
            case RIGHT:
                player.solidArea.x += player.speed;
                checkCollision(target, i);
                break;
        }
    }

    private void checkCollision(NPC[] target, int i) {
        if (player.solidArea.intersects(target[i].getSolidArea())) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void restoreDefaultAreaValues(NPC target) {
        player.solidArea.x = player.solidAreaDefaultX;
        player.solidArea.y = player.solidAreaDefaultY;
        target.getSolidArea().x = target.getSolidAreadDefaultX();
        target.getSolidArea().y = target.getSolidAreadDefaultY();
    }

    public int getIndex() {
        return index;
    }
}
