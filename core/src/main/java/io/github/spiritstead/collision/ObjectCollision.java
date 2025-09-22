package io.github.spiritstead.collision;

import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.GameObject;

public class ObjectCollision implements Collision {
    private GamePanel gp;
    private ObjectColliadable entity;

    private int index;

    private GameObject gameObject;

    public ObjectCollision(GamePanel gp, ObjectColliadable entity) {
        this.gp = gp;
        this.entity = entity;
    }

    public void check() {
        this.index = 9999;
        boolean player = entity instanceof Player;

        for (int i = 0; i < this.gp.system.aSetter.objects.length; i++) {
            if (this.gp.system.aSetter.objects[i] != null) {
                this.gameObject = this.gp.system.aSetter.objects[i];
                intializeEntitySolidArea();
                initializeObjectSolidArea(i);
                checkCollisionForAllDirections(player, i);
                restoreDefaultAreaValues(i);
            }
        }
    }

    private void checkCollisionForAllDirections(boolean player, int i) {
        switch (entity.getDirection()) {
            case UP:
                checkUpCollision(player, i);
                break;
            case DOWN:
                checkDownCollision(player, i);
                break;
            case LEFT:
                checkLeftCollision(player, i);
                break;
            case RIGHT:
                checkRightCollision(player, i);
                break;
        }
    }

    private void restoreDefaultAreaValues(int i) {
        entity.getSolidArea().x = entity.getSolidAreadDefaultX();
        entity.getSolidArea().y = entity.getSolidAreadDefaultY();
        gameObject.solidArea.x = gameObject.solidAreaDefaultX;
        gameObject.solidArea.y = gameObject.solidAreaDefaultY;
    }

    private void checkRightCollision(boolean player, int i) {
        entity.getSolidArea().x += entity.getSpeed();
        if (entity.getSolidArea().intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.setCollisionOn(true);
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkLeftCollision(boolean player, int i) {
        entity.getSolidArea().x -= entity.getSpeed();
        if (entity.getSolidArea().intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.setCollisionOn(true);
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkDownCollision(boolean player, int i) {
        entity.getSolidArea().y -= entity.getSpeed();
        if (entity.getSolidArea().intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.setCollisionOn(true);
            }
            if (player) {
                index = i;
            }
        }
    }

    private void checkUpCollision(boolean player, int i) {
        entity.getSolidArea().y += entity.getSpeed();
        if (entity.getSolidArea().intersects(gameObject.solidArea)) {
            if (gameObject.collision == true) {
                entity.setCollisionOn(true);
            }
            if (player) {
                index = i;
            }
        }
    }

    private void initializeObjectSolidArea(int i) {
        gameObject.solidArea.x = gameObject.worldX + gameObject.solidArea.x;
        gameObject.solidArea.y = gameObject.worldY + gameObject.solidArea.y;
    }

    private void intializeEntitySolidArea() {
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
    }

    public int getIndex() {
        return index;
    }

}
