package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.ScreenSetting;

public class Sprite {
    private final com.badlogic.gdx.graphics.g2d.Sprite sprite;
    private Float x = null;
    private Float y = null;

    public Sprite(String filePath, int scale) {
        this.sprite = new com.badlogic.gdx.graphics.g2d.Sprite(new Texture(filePath));
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
    }

    public Sprite(String filePath, float width, float height) {
        this.sprite = new com.badlogic.gdx.graphics.g2d.Sprite(new Texture(filePath));
        this.sprite.setSize(width, height);
        this.x = (float) ScreenSetting.SCREEN_WIDTH / 2 - this.sprite.getWidth() / 2;
        this.y = (float) ScreenSetting.SCREEN_HEIGHT / 2 - this.sprite.getHeight() / 4;
        this.sprite.setPosition(x, y);
    }

    public void draw(float screenX, float screenY) {
        sprite.setPosition(screenX, screenY);
        sprite.draw(Game.batch);
    }

    public void draw() {
        sprite.draw(Game.batch);
    }

    public float getWidth() { return this.sprite.getWidth(); }
    public Float getX() { return this.x; }
    public Float getY() { return this.y; }
    public com.badlogic.gdx.graphics.g2d.Sprite getSprite() { return sprite; }
}
