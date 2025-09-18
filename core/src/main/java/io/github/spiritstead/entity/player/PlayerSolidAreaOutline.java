package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.main.GamePanel;

public class PlayerSolidAreaOutline {
    GamePanel gp;
    Sprite sprite;
    Player player;

    public PlayerSolidAreaOutline(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        generateSolidAreaOutline();
    }

    private void generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(gp.sSetting.tileSize, gp.sSetting.tileSize, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(player.solidArea.x, gp.sSetting.tileSize - player.solidArea.height, player.solidArea.width, player.solidArea.height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.sprite = solidAreaSprite;
    }

    public void draw() {
        gp.batch.draw(sprite, player.screenX, player.screenY);

    }
}
