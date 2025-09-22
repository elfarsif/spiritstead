package io.github.spiritstead.collision;

import io.github.spiritstead.entity.PlayerInteractable;
import io.github.spiritstead.entity.player.Player;

public class NPCCollision implements Collision {
    private int index;

    private Player player;
    private PlayerInteractable[] targets;

    public NPCCollision(Player player, PlayerInteractable[] targets) {
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

    private void initializeTargetEntitySolidArea(PlayerInteractable target) {
        target.getSolidArea().x = target.getWorldX() + target.getSolidArea().x;
        target.getSolidArea().y = target.getWorldY() + target.getSolidArea().y;
    }

    private void checkCollisionForAllDirections(PlayerInteractable[] target, int i) {
        switch (player.direction) {
            case UP:
                checkUpCollision(target, i);
                break;
            case DOWN:
                checkDownCollision(target, i);
                break;
            case LEFT:
                checkLeftCollision(target, i);
                break;
            case RIGHT:
                checkRightCollision(target, i);
                break;
        }
    }

    private void restoreDefaultAreaValues(PlayerInteractable target) {
        player.solidArea.x = player.solidAreaDefaultX;
        player.solidArea.y = player.solidAreaDefaultY;
        target.getSolidArea().x = target.getSolidAreadDefaultX();
        target.getSolidArea().y = target.getSolidAreadDefaultY();
    }

    private void checkRightCollision(PlayerInteractable[] target, int i) {
        player.solidArea.x += player.speed;
        if (player.solidArea.intersects(target[i].getSolidArea())) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkLeftCollision(PlayerInteractable[] target, int i) {
        player.solidArea.x -= player.speed;
        if (player.solidArea.intersects(target[i].getSolidArea())) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkDownCollision(PlayerInteractable[] target, int i) {
        player.solidArea.y -= player.speed;
        if (player.solidArea.intersects(target[i].getSolidArea())) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkUpCollision(PlayerInteractable[] target, int i) {
        player.solidArea.y += player.speed;
        if (player.solidArea.intersects(target[i].getSolidArea())) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    public int getIndex() {
        return index;
    }
}
