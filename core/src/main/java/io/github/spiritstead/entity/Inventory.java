package io.github.spiritstead.entity;

import io.github.spiritstead.object.GameObject;

import java.util.ArrayList;

public class Inventory {
    ArrayList<GameObject> items = new ArrayList<>();

    public void add(GameObject object) {
        this.items.add(object);
    }

    @Override
    public String toString() {
        return "Inventory{" +
            "items=" + items +
            '}';
    }
}
