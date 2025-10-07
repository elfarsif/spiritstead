package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class Door implements GameObject {
    public Sprite image;
    public String name;
    public boolean collision = false;
    SolidArea solidArea = new SolidArea(0, 0, 48, 48);
    ObjectDrawer objectDrawer;
    private WorldPosition worldPosition = new WorldPosition();

    public Door() {
        name = "Door";
        image = new Sprite(new Texture("objects/door.png"));
        collision = true;
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    public Door(int worldX, int worldY) {
        this();
        worldPosition.setXY(worldX, worldY);
    }

    public void draw(SpriteBatch batch, GamePanel gp) {
        objectDrawer.draw(image);
    }

    @Override
    public void interact() {
        if (Game.player.hasKey > 0) {
            for (int i = 0; i < Game.aSetter.obj.size(); i++) {
                if (Game.aSetter.obj.get(i) == this) {
                    Game.aSetter.obj.remove(i);
                }
            }
            Game.player.hasKey--;
            Game.ui.gameScreenUI.showMessage("You opened the door!");
        } else {
            Game.ui.gameScreenUI.showMessage("You need a key");
        }
    }
    @Override
    public Sprite getImage() {
        return this.image;
    }
    @Override
    public SolidArea getSolidArea() {
        return this.solidArea;
    }

    @Override
    public void setCollisionOn(boolean collisionOn) {

    }

    @Override
    public WorldPosition getWorldPosition() {
        return this.worldPosition;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public Direction getDirection() {
        return null;
    }
}
