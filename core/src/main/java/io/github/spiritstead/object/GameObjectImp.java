package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.Direction;
import io.github.spiritstead.entity.SolidArea;
import io.github.spiritstead.entity.WorldPosition;

public class GameObjectImp implements GameObject {
    private final Sprite image;
    private final SolidArea solidArea;
    private final ObjectDrawer objectDrawer;
    private final WorldPosition worldPosition;
    private final Interactable interactable;

    public GameObjectImp(Sprite image, SolidArea solidArea, WorldPosition worldPosition, Interactable interactable) {
        this.solidArea = solidArea;
        this.image = image;
        this.worldPosition = worldPosition;
        this.objectDrawer = new ObjectDrawer(worldPosition);
        this.interactable = interactable;
    }

    @Override
    public void draw(SpriteBatch batch) { this.objectDrawer.draw(image); }
    @Override
    public void interact() {
        System.out.println("interact");
//        this.interactable.interact();
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
