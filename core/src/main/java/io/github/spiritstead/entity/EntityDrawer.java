package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.player.Player;
import io.github.spiritstead.main.GamePanel;

public class EntityDrawer {
    private GamePanel gp;
    SpriteBatch batch;
    Sprite sprite;
    Entity entity;
    int screenX, screenY;

    public EntityDrawer(GamePanel gp, Entity entity) {
        this.gp = gp;
        this.entity = entity;
        this.batch = gp.batch;
    }

    public void draw() {
        initialiazeScreenPositionRelativeToPlayer();
        if ((entity instanceof Player) || entityIsWithinScreenBounds()) {
            updateSprite();
            if (entity instanceof Player) {
                batch.draw(sprite, entity.screenX, entity.screenY, gp.sSetting.TILE_SIZE, gp.sSetting.TILE_SIZE);
            } else {
                batch.draw(sprite, screenX, screenY, gp.sSetting.TILE_SIZE, gp.sSetting.TILE_SIZE);
            }
        }
    }

    private void updateSprite() {
        sprite = null;
        switch (entity.direction) {
            case UP:
                sprite = entity.frames.get(Direction.UP)[entity.spriteNum - 1];
                break;
            case DOWN:
                sprite = entity.frames.get(Direction.DOWN)[entity.spriteNum - 1];
                break;
            case LEFT:
                sprite = entity.frames.get(Direction.LEFT)[entity.spriteNum - 1];
                break;
            case RIGHT:
                sprite = entity.frames.get(Direction.RIGHT)[entity.spriteNum - 1];
                break;
            default:
                sprite = entity.down1;
        }
    }

    private boolean entityIsWithinScreenBounds() {
        return entity.worldX + gp.sSetting.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
            entity.worldX - gp.sSetting.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
            entity.worldY + gp.sSetting.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
            entity.worldY - gp.sSetting.TILE_SIZE < gp.player.worldY + gp.player.screenY;
    }

    private void initialiazeScreenPositionRelativeToPlayer() {
        screenX = entity.worldX - gp.player.worldX + gp.player.screenX;
        screenY = entity.worldY - gp.player.worldY + gp.player.screenY;
    }
}
