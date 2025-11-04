package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.EventType;

public final class Tree implements GameObject {
    private final Sprite image;
    private final SolidArea solidArea;
    private final ObjectDrawer objectDrawer;
    private final WorldPosition worldPosition;
    private final EventBus eventBus;

    private int health;

    public Tree(SolidArea solidArea, WorldPosition worldPosition, EventBus eventBus, Sprite image, int health) {
        this.solidArea = solidArea;
        this.image = image;
        this.worldPosition = worldPosition;
        this.eventBus = eventBus;
        this.objectDrawer = new ObjectDrawer(worldPosition);

        this.health = health;
    }

    @Override
    public void draw(SpriteBatch batch) { objectDrawer.draw(image); }
    @Override
    public Sprite getImage() {
        return this.image;
    }
    @Override
    public void interact() {
        this.health--;
        if (health == 0) {
            this.eventBus.publish(EventType.TREE_REMOVED, this);
        }
    }

    @Override
    public SolidArea getSolidArea() { return this.solidArea; }
    @Override
    public WorldPosition getWorldPosition() { return this.worldPosition; }
    @Override
    public int getSpeed() { return 0; }
    @Override
    public Direction getDirection() { return null; }
}
