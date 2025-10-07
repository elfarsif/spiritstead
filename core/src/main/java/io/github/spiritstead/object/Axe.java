package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class Axe implements GameObject {
    private Sprite image;
    public boolean collision = false;
    SolidArea solidArea = new SolidArea(0, 0, 48, 48);
    ObjectDrawer objectDrawer;
    private WorldPosition worldPosition = new WorldPosition();

    public Axe() {
        this.image = new Sprite(new Texture("objects/axe.png"));
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    public Axe(int worldX, int worldY) {
        this();
        worldPosition.setXY(worldX, worldY);
    }

    @Override
    public void draw(SpriteBatch batch, GamePanel gp) {
        objectDrawer.draw(image);
    }

    @Override
    public void interact() {
        Game.ui.gameScreenUI.showMessage("You got an axe!");
        Game.player.inventory.add(this);
        Game.player.inventory.selectedItem = this;
        System.out.println(Game.player.inventory);
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) == this) {
                Game.aSetter.obj.remove(i);
            }
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
