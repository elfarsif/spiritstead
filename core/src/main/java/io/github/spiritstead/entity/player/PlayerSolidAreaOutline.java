package io.github.spiritstead.entity.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

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
        Pixmap solidAreaPixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.WHITE);
        solidAreaPixmap.drawRectangle(player.getSolidArea().getRect().x, ScreenSetting.TILE_SIZE - player.getSolidArea().getRect().height, player.getSolidArea().getRect().width, player.getSolidArea().getRect().height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.sprite = solidAreaSprite;
        Game
    }

    public void draw() {
        gp.batch.draw(sprite, player.screenPosition.getX(), player.screenPosition.getY());

    }
}
