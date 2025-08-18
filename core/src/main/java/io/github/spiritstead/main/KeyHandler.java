package io.github.spiritstead.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
/*
This class handles the key inputs for the game.
It extends InputAdapter to handle key events.
It can be used to check if a key is pressed or released.
 */
public class KeyHandler extends InputAdapter {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public boolean keyDown(int code){
        if (code == Input.Keys.W){
            upPressed = true;
        }
        else if (code == Input.Keys.S){
            downPressed = true;
        }
        else if (code == Input.Keys.A){
            leftPressed = true;
        }
        else if (code == Input.Keys.D){
            rightPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int code) {
        if (code == Input.Keys.W){
            upPressed = false;
        }
        else if (code == Input.Keys.S){
            downPressed = false;
        }
        else if (code == Input.Keys.A){
            leftPressed = false;
        }
        else if (code == Input.Keys.D){
            rightPressed = false;
        }

        return true;
    }
}
