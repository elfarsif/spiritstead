package io.github.spiritstead.collision;

import io.github.spiritstead.entity.ObjectColliadable;
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

        for (int i = 0; i < this.gp.aSetter.objects.length; i++) {
            if (this.gp.aSetter.objects[i] != null) {
                this.gameObject = this.gp.aSetter.objects[i];
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
                entity.getSolidArea().getRect().y += entity.getSpeed();
                checkCollision(player, i);
                break;
            case DOWN:
                entity.getSolidArea().getRect().y -= entity.getSpeed();
                checkCollision(player, i);
                break;
            case LEFT:
                entity.getSolidArea().getRect().x -= entity.getSpeed();
                checkCollision(player, i);
                break;
            case RIGHT:
                entity.getSolidArea().getRect().x += entity.getSpeed();
                checkCollision(player, i);
                break;
        }
    }

    private void restoreDefaultAreaValues(int i) {
        entity.getSolidArea().getRect().x = entity.getSolidArea().getDefaultX();
        entity.getSolidArea().getRect().y = entity.getSolidArea().getDefaultY();
        gameObject.solidArea.x = gameObject.solidAreaDefaultX;
        gameObject.solidArea.y = gameObject.solidAreaDefaultY;
    }

    private void checkCollision(boolean player, int i) {
        if (entity.getSolidArea().getRect().intersects(gameObject.solidArea)) {
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
        entity.getSolidArea().getRect().x = entity.getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entity.getSolidArea().getRect().y = entity.getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }

    public int getIndex() {
        return index;
    }

}
