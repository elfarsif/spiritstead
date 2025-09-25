package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.ScreenSetting;

public class PlayerDrawer {
    private SpriteBatch batch;
    private Sprite sprite;
    private Player player;

    public PlayerDrawer(SpriteBatch batch, Player player) {
        this.player = player;
        this.batch = batch;
    }

    public void draw() {
        updateSprite();
        batch.draw(sprite, player.screenPosition.getX(), player.screenPosition.getY(), ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE);
    }

    private void updateSprite() {
        sprite = null;
        switch (player.direction) {
            case UP:
                sprite = player.frames.get(Direction.UP)[player.spriteNum - 1];
                break;
            case DOWN:
                sprite = player.frames.get(Direction.DOWN)[player.spriteNum - 1];
                break;
            case LEFT:
                sprite = player.frames.get(Direction.LEFT)[player.spriteNum - 1];
                break;
            case RIGHT:
                sprite = player.frames.get(Direction.RIGHT)[player.spriteNum - 1];
                break;
            default:
                sprite = player.down1;
        }
    }

}
