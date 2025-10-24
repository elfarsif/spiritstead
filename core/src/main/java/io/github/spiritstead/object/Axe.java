package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;
import io.github.spiritstead.main.Game;

public class Axe implements GameObject {
    private final Sprite image;
    private final SolidArea solidArea;
    private final ObjectDrawer objectDrawer;
    private final WorldPosition worldPosition;

    public Axe(Sprite image, SolidArea solidArea, WorldPosition worldPosition) {
        this.solidArea = solidArea;
        this.image = image;
        this.worldPosition = worldPosition;
        this.objectDrawer = new ObjectDrawer(worldPosition);
    }

    @Override
    public void draw(SpriteBatch batch) { objectDrawer.draw(image); }

    @Override
    public void interact() {
        Game.ui.gameUIScreen.showMessage("You got an axe!");
        Game.player.addToInventory(this);
        Game.player.selectedItem(this);
        for (int i = 0; i < Game.aSetter.obj.size(); i++) {
            if (Game.aSetter.obj.get(i) == this) {
                Game.aSetter.obj.remove(i);
            }
        }
    }

    @Override
    public Sprite getImage() { return this.image; }
    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public int getSpeed() { return 0; }
    @Override
    public Direction getDirection() { return null; }

}
