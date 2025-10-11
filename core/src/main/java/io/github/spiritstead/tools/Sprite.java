package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.Texture;
import io.github.spiritstead.main.Game;

public class Sprite {
    private final com.badlogic.gdx.graphics.g2d.Sprite sprite;

    public Sprite(String filePath, int scale) {
        this.sprite = new com.badlogic.gdx.graphics.g2d.Sprite(new Texture(filePath));
        this.sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
    }

    public void draw(float screenX, float screenY) {
        sprite.setPosition(screenX, screenY);
        sprite.draw(Game.batch);
    }

    public com.badlogic.gdx.graphics.g2d.Sprite getSprite() { return sprite; }
}
