package io.github.spiritstead.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.spiritstead.main.ui.TitleScreenOptions;

/*
This class handles the key inputs for the game.
It extends InputAdapter to handle key events.
It can be used to check if a key is pressed or released.
 */
public class KeyHandler extends InputAdapter {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public boolean keyDown(int code){
        //Title State
        if (gp.gameState == GameState.TITLESTATE){
            //move this logic to a seperate class for title state
            if (code == Input.Keys.W){
                gp.ui.titleScreenUI.commandNum--;
                if (gp.ui.titleScreenUI.commandNum<TitleScreenOptions.NEW_GAME.getValue()){
                    gp.ui.titleScreenUI.commandNum = TitleScreenOptions.QUIT.getValue();
                }
            }
            else if (code == Input.Keys.S){
                gp.ui.titleScreenUI.commandNum++;
                if (gp.ui.titleScreenUI.commandNum>TitleScreenOptions.QUIT.getValue()){
                    gp.ui.titleScreenUI.commandNum = TitleScreenOptions.NEW_GAME.getValue();
                }
            }
            if (code == Input.Keys.ENTER){
                if (gp.ui.titleScreenUI.commandNum == TitleScreenOptions.NEW_GAME.getValue()){
                    gp.gameState = GameState.PLAYSTATE;
                }
                if (gp.ui.titleScreenUI.commandNum == TitleScreenOptions.LOAD_GAME.getValue()){

                }
                if (gp.ui.titleScreenUI.commandNum == TitleScreenOptions.QUIT.getValue()){
                    System.exit(0);
                }
            }
        }


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
        }else if (code == Input.Keys.P){
            if (gp.gameState == GameState.PLAYSTATE){
                gp.gameState = GameState.PAUSESTATE;
            }else if (gp.gameState == GameState.PAUSESTATE){
                gp.gameState = GameState.PLAYSTATE;
            }
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
