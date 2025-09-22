package io.github.spiritstead.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EntitySpriteLoader {
    PlayerInteractable entity;

    public EntitySpriteLoader(PlayerInteractable entity) {
        this.entity = entity;
    }

    public void load() {
        entity.setUp1(new Sprite(new Texture("player/up1.png")));
        entity.setUp2(new Sprite(new Texture("player/up2.png")));
        entity.setDown1(new Sprite(new Texture("player/down1.png")));
        entity.setDown2(new Sprite(new Texture("player/down2.png")));
        entity.setLeft1(new Sprite(new Texture("player/left1.png")));
        entity.setLeft2(new Sprite(new Texture("player/left2.png")));
        entity.setRight1(new Sprite(new Texture("player/right1.png")));
        entity.setRight2(new Sprite(new Texture("player/right2.png")));
    }
}
