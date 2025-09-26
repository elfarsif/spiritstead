package io.github.spiritstead.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    int e1WorldX;
    int e1WorldY;
    private Sprite solidAreaOutlineSprite;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 0;
        eventRect.y = 0;
        eventRect.width = ScreenSetting.TILE_SIZE;
        eventRect.height = ScreenSetting.TILE_SIZE;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

        e1WorldX = 3 * ScreenSetting.TILE_SIZE;
        e1WorldY = 3 * ScreenSetting.TILE_SIZE;

        generateSolidAreaOutline();

    }

    public void checkEvent() {
        if (hit(3, 3, Direction.ANY)) {
            gp.ui.gameScreenUI.showMessage("You have hit an event");
        }
    }

    public boolean hit(int eventCol, int eventRow, Direction reqDirection) {
        boolean hit = false;

        gp.player.getSolidArea().getRect().x = gp.player.getWorldPosition().getX() + gp.player.getSolidArea().getRect().x;
        gp.player.getSolidArea().getRect().y = gp.player.getWorldPosition().getY() + gp.player.getSolidArea().getRect().y;
        eventRect.x = eventCol * ScreenSetting.TILE_SIZE + eventRect.x;
        eventRect.y = eventRow * ScreenSetting.TILE_SIZE + eventRect.y;

        if (gp.player.getSolidArea().getRect().intersects(eventRect)) {
            if ((gp.player.direction == reqDirection)) {
                hit = true;
            } else if (reqDirection == Direction.ANY) {
                hit = true;

            }
        }
        gp.player.getSolidArea().getRect().x = gp.player.getSolidArea().getDefaultX();
        gp.player.getSolidArea().getRect().y = gp.player.getSolidArea().getDefaultY();
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultX;

        return hit;

    }

    private void generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(ScreenSetting.TILE_SIZE, ScreenSetting.TILE_SIZE, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.RED);
        solidAreaPixmap.drawRectangle(eventRect.x, eventRect.y, eventRect.width, eventRect.height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.solidAreaOutlineSprite = solidAreaSprite;
    }

    public void draw(SpriteBatch batch) {
        //calculate where on the screen to draw the tile relative to player, from tile manage class
        int screenX = e1WorldX - gp.player.getWorldPosition().getX() + gp.player.screenPosition.getX();
        int screenY = e1WorldY - gp.player.getWorldPosition().getY() + gp.player.screenPosition.getY();

        batch.draw(solidAreaOutlineSprite, screenX, screenY);

    }
}
