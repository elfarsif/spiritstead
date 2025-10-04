package io.github.spiritstead.entity;

import io.github.spiritstead.object.Axe;
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

    public boolean contains(Class objectClass) {
        for (GameObject item : items) {
            if (item.getClass() == objectClass) {
                return true;
            }
        }
        return false;
    }
}
