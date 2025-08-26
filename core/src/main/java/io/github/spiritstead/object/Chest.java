package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chest extends GameObject {
    public Chest(){
        name = "chest";
        image = new Sprite(new Texture("objects/chest.png"));
        collision = true;
    }
}
