package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.main.ScreenSetting;

public class PlayerDrawer {
    private SpriteBatch batch;
    private Sprite sprite;
    private Sprites sprites;
    private Player player;
    private int spriteNum = 1;

    public PlayerDrawer(SpriteBatch batch, Player player, Sprites sprites) {
        this.player = player;
        this.sprites = sprites;
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
                sprite = sprites.frames.get(Direction.UP)[spriteNum - 1];
                break;
            case DOWN:
                sprite = sprites.frames.get(Direction.DOWN)[spriteNum - 1];
                break;
            case LEFT:
                sprite = sprites.frames.get(Direction.LEFT)[spriteNum - 1];
                break;
            case RIGHT:
                sprite = sprites.frames.get(Direction.RIGHT)[spriteNum - 1];
                break;
            default:
                sprite = sprites.down1;
        }
    }

}
