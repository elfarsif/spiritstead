package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;
import io.github.spiritstead.main.GamePanel;

public class Tree implements GameObject {
    private Sprite image;
    private SolidArea solidArea = new SolidArea(0, 0, 48, 48);
    private WorldPosition worldPosition;
    private ObjectDrawer objectDrawer;
    private int health;

    public Tree(WorldPosition worldPosition) {
        this.image = new Sprite(new Texture("tiles/tree.png"));
        this.worldPosition = worldPosition;
        this.objectDrawer = new ObjectDrawer(worldPosition);
        this.health = 2;
    }

    @Override
    public void draw(SpriteBatch batch, GamePanel gp) { objectDrawer.draw(image); }
    @Override
    public Sprite getImage() {
        return this.image;
    }
    @Override
    public void interact() {
        this.health--;
        System.out.println("currentHealth : " + health);

        if (health == 0) {
            removeTree();
        }
    }

    private void removeTree() {
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) == this) {
                Game.aSetter.obj.remove(i);
            }
        }
    }

    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public void setCollisionOn(boolean collisionOn) { }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public int getSpeed() { return 0; }
    @Override
    public Direction getDirection() { return null; }
}
