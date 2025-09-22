package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

public class EntityDrawer {
    private GamePanel gp;
    SpriteBatch batch;
    Sprite sprite;
    PlayerInteractable entity;
    int screenX, screenY;

    public EntityDrawer(GamePanel gp, PlayerInteractable entity) {
        this.gp = gp;
        this.entity = entity;
        this.batch = gp.batch;
    }

    public void draw() {
        initialiazeScreenPositionRelativeToPlayer();
        if (entityIsWithinScreenBounds()) {
            updateSprite();

            batch.draw(sprite, screenX, screenY, ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);

        }
    }

    private void updateSprite() {
        sprite = null;
        switch (entity.getDirection()) {
            case UP:
                sprite = entity.getFrames().get(Direction.UP)[entity.getSpriteNum() - 1];
                break;
            case DOWN:
                sprite = entity.getFrames().get(Direction.DOWN)[entity.getSpriteNum() - 1];
                break;
            case LEFT:
                sprite = entity.getFrames().get(Direction.LEFT)[entity.getSpriteNum() - 1];
                break;
            case RIGHT:
                sprite = entity.getFrames().get(Direction.RIGHT)[entity.getSpriteNum() - 1];
                break;
            default:
                sprite = entity.getDown1();
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return entity.getWorldX() + ScreenSetting.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
            entity.getWorldX() - ScreenSetting.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
            entity.getWorldY() + ScreenSetting.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
            entity.getWorldY() - ScreenSetting.TILE_SIZE < gp.player.worldY + gp.player.screenY;
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        screenX = entity.getWorldX() - gp.player.worldX + gp.player.screenX;
        screenY = entity.getWorldY() - gp.player.worldY + gp.player.screenY;
    }
}
