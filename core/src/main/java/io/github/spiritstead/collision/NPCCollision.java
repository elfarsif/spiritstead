package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Entity;
import io.github.spiritstead.entity.player.Player;

public class NPCCollision implements Collision {
    private int index;

    private Player player;
    private Entity[] targets;

    public NPCCollision(Player player, Entity[] targets) {
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

    private void initializeTargetEntitySolidArea(Entity target) {
        target.solidArea.x = target.worldX + target.solidArea.x;
        target.solidArea.y = target.worldY + target.solidArea.y;
    }

    private void checkCollisionForAllDirections(Entity[] target, int i) {
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

    private void restoreDefaultAreaValues(Entity target) {
        player.solidArea.x = player.solidAreaDefaultX;
        player.solidArea.y = player.solidAreaDefaultY;
        target.solidArea.x = target.solidAreaDefaultX;
        target.solidArea.y = target.solidAreaDefaultY;
    }

    private void checkRightCollision(Entity[] target, int i) {
        player.solidArea.x += player.speed;
        if (player.solidArea.intersects(target[i].solidArea)) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkLeftCollision(Entity[] target, int i) {
        player.solidArea.x -= player.speed;
        if (player.solidArea.intersects(target[i].solidArea)) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkDownCollision(Entity[] target, int i) {
        player.solidArea.y -= player.speed;
        if (player.solidArea.intersects(target[i].solidArea)) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    private void checkUpCollision(Entity[] target, int i) {
        player.solidArea.y += player.speed;
        if (player.solidArea.intersects(target[i].solidArea)) {
            player.collisionOn = true;
            this.index = i;
        }
    }

    public int getIndex() {
        return index;
    }
}
