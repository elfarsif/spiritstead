package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.entity.Entity;

public class PlayerSpriteLoader {
    Player player;

    public PlayerSpriteLoader(Player player) {
        this.player = player;
    }

    public void load() {
        player.up1 = new Sprite(new Texture("player/up1.png"));
        player.up2 = new Sprite(new Texture("player/up2.png"));
        player.down1 = new Sprite(new Texture("player/down1.png"));
        player.down2 = new Sprite(new Texture("player/down2.png"));
        player.left1 = new Sprite(new Texture("player/left1.png"));
        player.left2 = new Sprite(new Texture("player/left2.png"));
        player.right1 = new Sprite(new Texture("player/right1.png"));
        player.right2 = new Sprite(new Texture("player/right2.png"));
    }
}
