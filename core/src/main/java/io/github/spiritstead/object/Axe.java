package io.github.spiritstead.object;

import io.github.spiritstead.entity.EventBus;
import io.github.spiritstead.main.EventType;
import io.github.spiritstead.main.Game;

public final class Axe implements Interactable {
    private final EventBus eventBus;

    public Axe(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void interact(GameObject gameObject) {
        Game.ui.gameUIScreen.showMessage("You got an axe!");
        eventBus.publish(EventType.ADD_TO_INVENTORY, gameObject);
        eventBus.publish(EventType.OBJECT_REMOVAL, gameObject);
    }

}
