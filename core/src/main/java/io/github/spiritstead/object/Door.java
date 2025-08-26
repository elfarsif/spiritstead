package io.github.spiritstead.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Door extends GameObject {

    public Door(){
        name = "Door";
        image = new Sprite(new Texture("objects/door.png"));
        collision = true;
    }

}
