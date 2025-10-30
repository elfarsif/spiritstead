package io.github.spiritstead.object;

import io.github.spiritstead.entity.Subscriber;
import io.github.spiritstead.main.EventType;
import io.github.spiritstead.main.Game;

import java.util.List;

public final class GameObjects implements Subscriber {
    private final List<GameObject> gameObjects;

    public GameObjects(List<GameObject> gameObjects) { this.gameObjects = gameObjects; }

    public void draw() {
        for (GameObject gameObject : this.gameObjects) {
            if (gameObject != null) {
                gameObject.draw(Game.batch);
            }
        }
    }
    public void add(GameObject gameObject) { this.gameObjects.add(gameObject); }
    public int size() { return this.gameObjects.size(); }
    public GameObject get(int i) { return this.gameObjects.get(i); }
    public void remove(int i) { this.gameObjects.remove(i); }
    @Override
    public void onEventBus(EventType eventType) {
        System.out.println("in game object triggeredk");

    }
}
