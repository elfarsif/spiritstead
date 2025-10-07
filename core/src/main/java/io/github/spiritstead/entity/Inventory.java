package io.github.spiritstead.entity;

import io.github.spiritstead.object.Axe;
import io.github.spiritstead.object.GameObject;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<GameObject> items;
    public GameObject selectedItem;

    public Inventory(ArrayList<GameObject> items) {
        this.items = items;
    }
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
    public int size() { return items.size(); }
    public void setSelectedItemToPrev() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == selectedItem && i > 0) {
                int temp = i - 1;
                selectedItem = items.get(temp);
                break;
            }
        }

    }
    public void setSelectedItemToNext() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == selectedItem && i < items.size() - 1) {
                int temp = i + 1;
                selectedItem = items.get(temp);
                break;
            }
        }
    }
}
