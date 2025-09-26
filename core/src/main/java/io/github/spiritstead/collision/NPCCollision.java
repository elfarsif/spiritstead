package io.github.spiritstead.collision;

import io.github.spiritstead.entity.npc.NPC;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.Game;

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
        this.player.getSolidArea().getRect().x = this.player.getValues().getWorldPosition().getX() + this.player.getSolidArea().getRect().x;
        this.player.getSolidArea().getRect().y = this.player.getValues().getWorldPosition().getY() + this.player.getSolidArea().getRect().y;
    }

    private void initializeTargetEntitySolidArea(NPC target) {
        target.getSolidArea().getRect().x = target.getValues().getWorldPosition().getX() + target.getSolidArea().getRect().x;
        target.getSolidArea().getRect().y = target.getValues().getWorldPosition().getY() + target.getSolidArea().getRect().y;
    }

    private void checkCollisionForAllDirections(NPC[] target, int i) {
        switch (player.getValues().direction) {
            case UP:
                player.getSolidArea().getRect().y += player.getValues().speed;
                checkCollision(target, i);
                break;
            case DOWN:
                player.getSolidArea().getRect().y -= player.getValues().speed;
                checkCollision(target, i);
                break;
            case LEFT:
                player.getSolidArea().getRect().x -= player.getValues().speed;
                checkCollision(target, i);
                break;
            case RIGHT:
                player.getSolidArea().getRect().x += player.getValues().speed;
                checkCollision(target, i);
                break;
        }
    }

    private void checkCollision(NPC[] target, int i) {
        if (player.getSolidArea().getRect().intersects(target[i].getSolidArea().getRect())) {
            player.setCollisionOn(true);
            this.index = i;
        }
    }

    private void restoreDefaultAreaValues(NPC target) {
        player.getSolidArea().getRect().x = player.getSolidArea().getDefaultX();
        player.getSolidArea().getRect().y = player.getSolidArea().getDefaultY();
        target.getSolidArea().getRect().x = target.getSolidArea().getDefaultX();
        target.getSolidArea().getRect().y = target.getSolidArea().getDefaultY();
    }

    public int getIndex() {
        return index;
    }
}
