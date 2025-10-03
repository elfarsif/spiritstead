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
    public String name;
    public boolean collision = false;
    SolidArea solidArea = new SolidArea(0, 0, 48, 48);
    ObjectDrawer objectDrawer;
    private WorldPosition worldPosition = new WorldPosition();

    public Axe() {
        name = "Axe";
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
        for (int i = 0; i < Game.aSetter.objects.length; i++) {
            if (Game.aSetter.objects[i] == this) {
                Game.aSetter.objects[i] = null;
                Game.player.inventory.add(this);
                System.out.println(Game.player.inventory);
            }
        }
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
