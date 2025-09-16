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
        eventRect.width = gp.sSetting.tileSize;
        eventRect.height = gp.sSetting.tileSize;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

        e1WorldX = 3 * gp.sSetting.tileSize;
        e1WorldY = 3 * gp.sSetting.tileSize;

        generateSolidAreaOutline();

    }

    public void checkEvent() {
        if (hit(3, 3, Direction.ANY)) {
            gp.system.ui.gameScreenUI.showMessage("You have hit an event");
        }
    }

    public boolean hit(int eventCol, int eventRow, Direction reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.sSetting.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.sSetting.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if ((gp.player.direction == reqDirection)) {
                hit = true;
            } else if (reqDirection == Direction.ANY) {
                hit = true;

            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultX;

        return hit;

    }

    private void generateSolidAreaOutline() {
        Pixmap solidAreaPixmap = new Pixmap(gp.sSetting.tileSize, gp.sSetting.tileSize, Pixmap.Format.RGBA8888);
        solidAreaPixmap.setColor(Color.RED);
        solidAreaPixmap.drawRectangle(eventRect.x, eventRect.y, eventRect.width, eventRect.height);
        Sprite solidAreaSprite = new Sprite(new Texture(solidAreaPixmap));
        solidAreaPixmap.dispose();
        this.solidAreaOutlineSprite = solidAreaSprite;
    }

    public void draw(SpriteBatch batch) {
        //calculate where on the screen to draw the tile relative to player, from tile manage class
        int screenX = e1WorldX - gp.player.worldX + gp.player.screenX;
        int screenY = e1WorldY - gp.player.worldY + gp.player.screenY;

        batch.draw(solidAreaOutlineSprite, screenX, screenY);

    }
}
