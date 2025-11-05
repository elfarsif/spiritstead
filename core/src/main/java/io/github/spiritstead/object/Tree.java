package io.github.spiritstead.object;

import io.github.spiritstead.entity.*;
import io.github.spiritstead.main.EventType;

public final class Tree implements Interactable {
    private final EventBus eventBus;
    private int health;

    public Tree(EventBus eventBus, int health) {
        this.eventBus = eventBus;
        this.health = health;
    }

    @Override
    public void interact(GameObject gameObject) {
        this.health--;
        System.out.println(this.health);
        if (health == 0) {
            this.eventBus.publish(EventType.TREE_REMOVED, gameObject);
        }
    }

}
