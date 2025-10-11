package io.github.spiritstead.main;

public class Resources {
    public static Resources instance;

    public static Resources getInstance() {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }

}
