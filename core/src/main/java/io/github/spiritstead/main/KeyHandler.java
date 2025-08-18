package io.github.spiritstead.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
/*
This class handles the key inputs for the game.
It extends InputAdapter to handle key events.
It can be used to check if a key is pressed or released.
 */
public class KeyHandler extends InputAdapter {
    public boolean upPressed;

    @Override
    public boolean keyDown(int code){

        if (code == Input.Keys.W){
            System.out.println("Pressed W");
            upPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int code) {
        if (code == Input.Keys.W){
            System.out.println("Released W");
            upPressed = false;
        }
        return true;
    }
}
