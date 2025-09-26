package io.github.spiritstead.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.spiritstead.cutscene.InputGate;
import io.github.spiritstead.screens.titleScreen.TitleScreenOptions;

/*
This class handles the key inputs for the game.
It extends InputAdapter to handle key events.
It can be used to check if a key is pressed or released.
 */
public class KeyHandler extends InputAdapter {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, tPressed;
    public InputGate inputGate;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
        this.inputGate = new InputGate();
    }

    @Override
    public boolean keyDown(int code) {
        if (inputGate.isOpen()) {
            //Title State
            if (Game.screens.screen == Game.screens.titleScreen) {
                titleState(code);
            }
            //PlayState
            else if (Game.screens.screen == Game.screens.gameScreen) {
                playState(code);
            }
            //Cutscene
            else if (Game.screens.screen == Game.screens.cutsceneScreen) {
                if (code == Input.Keys.SPACE) {
                    spacePressed = true;

                }
            }
            //Dialogue
            else if (Game.screens.screen == Game.screens.dialogueScreen) {
                if (code == Input.Keys.SPACE) {
                    spacePressed = true;
                    Game.screens.screen = Game.screens.gameScreen;
                    Game.ui.uiScreen = Game.ui.gameScreenUI;

                }
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
            if (Game.screens.screen == Game.screens.gameScreen) {
                System.out.println("pause state");
            }
        } else if (code == Input.Keys.T) {
            tPressed = !tPressed;
        }
    }

    private void titleState(int code) {
        //move this logic to a seperate class for title state
        if (code == Input.Keys.W) {
            Game.screens.titleScreen.commandNum--;
            if (Game.screens.titleScreen.commandNum < TitleScreenOptions.NEW_GAME.getValue()) {
                Game.screens.titleScreen.commandNum = TitleScreenOptions.QUIT.getValue();
            }
        } else if (code == Input.Keys.S) {
            Game.screens.titleScreen.commandNum++;
            if (Game.screens.titleScreen.commandNum > TitleScreenOptions.QUIT.getValue()) {
                Game.screens.titleScreen.commandNum = TitleScreenOptions.NEW_GAME.getValue();
            }
        }
        if (code == Input.Keys.ENTER) {
            if (Game.screens.titleScreen.commandNum == TitleScreenOptions.NEW_GAME.getValue()) {
                Game.screens.screen = Game.screens.cutsceneScreen;

            }
            if (Game.screens.titleScreen.commandNum == TitleScreenOptions.LOAD_GAME.getValue()) {
                Game.screens.screen = Game.screens.gameScreen;

            }
            if (Game.screens.titleScreen.commandNum == TitleScreenOptions.QUIT.getValue()) {
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
