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
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, tPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public boolean keyDown(int code) {
        //Title State
        if (gp.screenManager.screen == gp.screenManager.titleScreen) {
            titleState(code);
        }
        //PlayState
        else if (gp.screenManager.screen == gp.screenManager.gameScreen) {
            playState(code);
        }
        //Cutscene
        else if (gp.screenManager.screen == gp.screenManager.cutsceneScreen) {
            if (code == Input.Keys.SPACE) {
                spacePressed = true;

            }
        }

        return true;
    }

    private void playState(int code) {
        if (code == Input.Keys.W) {
            upPressed = true;
        } else if (code == Input.Keys.S) {
            downPressed = true;
        } else if (code == Input.Keys.A) {
            leftPressed = true;
        } else if (code == Input.Keys.D) {
            rightPressed = true;
        } else if (code == Input.Keys.P) {
            if (gp.screenManager.screen == gp.screenManager.gameScreen) {
                System.out.println("pause state");
            }
        } else if (code == Input.Keys.T) {
            tPressed = !tPressed;
        }
    }

    private void titleState(int code) {
        //move this logic to a seperate class for title state
        if (code == Input.Keys.W) {
            gp.screenManager.titleScreen.commandNum--;
            if (gp.screenManager.titleScreen.commandNum < TitleScreenOptions.NEW_GAME.getValue()) {
                gp.screenManager.titleScreen.commandNum = TitleScreenOptions.QUIT.getValue();
            }
        } else if (code == Input.Keys.S) {
            gp.screenManager.titleScreen.commandNum++;
            if (gp.screenManager.titleScreen.commandNum > TitleScreenOptions.QUIT.getValue()) {
                gp.screenManager.titleScreen.commandNum = TitleScreenOptions.NEW_GAME.getValue();
            }
        }
        if (code == Input.Keys.ENTER) {
            if (gp.screenManager.titleScreen.commandNum == TitleScreenOptions.NEW_GAME.getValue()) {
                gp.screenManager.screen = gp.screenManager.cutsceneScreen;

            }
            if (gp.screenManager.titleScreen.commandNum == TitleScreenOptions.LOAD_GAME.getValue()) {
                gp.screenManager.screen = gp.screenManager.gameScreen;

            }
            if (gp.screenManager.titleScreen.commandNum == TitleScreenOptions.QUIT.getValue()) {
                System.exit(0);
            }
        }
    }

    @Override
    public boolean keyUp(int code) {
        if (code == Input.Keys.W) {
            upPressed = false;
        } else if (code == Input.Keys.S) {
            downPressed = false;
        } else if (code == Input.Keys.A) {
            leftPressed = false;
        } else if (code == Input.Keys.D) {
            rightPressed = false;
        }

        return true;
    }
}
