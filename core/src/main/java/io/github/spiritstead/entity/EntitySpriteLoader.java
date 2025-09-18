package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EntitySpriteLoader {
    Entity entity;

    public EntitySpriteLoader(Entity entity) {
        this.entity = entity;
    }

    public void load() {
        entity.up1 = new Sprite(new Texture("player/up1.png"));
        entity.up2 = new Sprite(new Texture("player/up2.png"));
        entity.down1 = new Sprite(new Texture("player/down1.png"));
        entity.down2 = new Sprite(new Texture("player/down2.png"));
        entity.left1 = new Sprite(new Texture("player/left1.png"));
        entity.left2 = new Sprite(new Texture("player/left2.png"));
        entity.right1 = new Sprite(new Texture("player/right1.png"));
        entity.right2 = new Sprite(new Texture("player/right2.png"));
    }
}
