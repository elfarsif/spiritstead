package io.github.spiritstead.collision;

import io.github.spiritstead.entity.Collidable;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.object.GameObject;

public class ObjectCollision implements Collision {
    private Collidable entity;
    private int index;
    private GameObject gameObject;

    public ObjectCollision(Collidable entity) {
        this.entity = entity;
    }

    public void check() {
        this.index = 9999;
        boolean player = entity instanceof Player;

        for (int i = 0; i < Game.aSetter.objects.length; i++) {
            if (Game.aSetter.objects[i] != null) {
                this.gameObject = Game.aSetter.objects[i];
                intializeEntitySolidArea();
                initializeObjectSolidArea(i);
                checkCollisionForAllDirections(player, i);
                restoreDefaultAreaValues(i);
            }
        }
    }

    private void checkCollisionForAllDirections(boolean player, int i) {
        switch (entity.getValues().getDirection()) {
            case UP:
                entity.getSolidArea().getRect().y += entity.getValues().getSpeed();
                checkCollision(player, i);
                break;
            case DOWN:
                entity.getSolidArea().getRect().y -= entity.getValues().getSpeed();
                checkCollision(player, i);
                break;
            case LEFT:
                entity.getSolidArea().getRect().x -= entity.getValues().getSpeed();
                checkCollision(player, i);
                break;
            case RIGHT:
                entity.getSolidArea().getRect().x += entity.getValues().getSpeed();
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
        entity.getSolidArea().getRect().x = entity.getValues().getWorldPosition().getX() + entity.getSolidArea().getRect().x;
        entity.getSolidArea().getRect().y = entity.getValues().getWorldPosition().getY() + entity.getSolidArea().getRect().y;
    }

    public int getIndex() {
        return index;
    }

}
