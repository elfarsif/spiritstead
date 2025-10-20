package io.github.spiritstead.object;

import io.github.spiritstead.main.Game;

import java.util.List;

public class GameObjects {
    private final List<GameObject> gameObjects;

    public GameObjects(List<GameObject> gameObjects) { this.gameObjects = gameObjects; }

    public void draw() {
        for (GameObject gameObject : this.gameObjects) {
            if (gameObject != null) {
                gameObject.draw(Game.batch);
            }
        }
    }
}
