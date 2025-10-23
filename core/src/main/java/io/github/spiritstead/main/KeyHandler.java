package io.github.spiritstead.main;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import io.github.spiritstead.tools.InputGate;
import io.github.spiritstead.screens.titleScreen.TitleScreenOptions;

/*
This class handles the key inputs for the game.
It extends InputAdapter to handle key events.
It can be used to check if a key is pressed or released.
 */
public class KeyHandler implements InputProcessor {
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, tPressed,
            jPressed, kPressed;
    public InputGate inputGate;

    public KeyHandler() {
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
                dialogueScreen(code);

            }
        }

        return true;
    }

    private void dialogueScreen(int code) {
        if (code == Input.Keys.SPACE) {
            Game.aSetter.npcs[0].interact();
        } else if (code == Input.Keys.W) {
            Game.ui.playerDialogueUIScreen.optionCursor.optionNum--;
        } else if (code == Input.Keys.S) {
            Game.ui.playerDialogueUIScreen.optionCursor.optionNum++;
        } else if (code == Input.Keys.ENTER) {

        }
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
            System.out.println("pause state");
        } else if (code == Input.Keys.T) {
            tPressed = ! tPressed;
        } else if (code == Input.Keys.SPACE) {
            spacePressed = true;
        } else if (code == Input.Keys.J) {
            jPressed = true;
        } else if (code == Input.Keys.K) {
            kPressed = true;
        }
    }

    private void titleState(int code) {
        //move this logic to a seperate class for title state
        if (code == Input.Keys.W) {
            Game.screens.titleScreen.optionCursor.optionNum--;
            if (Game.screens.titleScreen.optionCursor.optionNum < TitleScreenOptions.NEW_GAME.getValue()) {
                Game.screens.titleScreen.optionCursor.optionNum = TitleScreenOptions.QUIT.getValue();
            }
        } else if (code == Input.Keys.S) {
            Game.screens.titleScreen.optionCursor.optionNum++;
            if (Game.screens.titleScreen.optionCursor.optionNum > TitleScreenOptions.QUIT.getValue()) {
                Game.screens.titleScreen.optionCursor.optionNum = TitleScreenOptions.NEW_GAME.getValue();
            }
        }
        if (code == Input.Keys.ENTER) {
            if (Game.screens.titleScreen.optionCursor.optionNum == TitleScreenOptions.NEW_GAME.getValue()) {
                Game.screens.setScreen(Game.screens.cutsceneScreen);

            }
            if (Game.screens.titleScreen.optionCursor.optionNum == TitleScreenOptions.LOAD_GAME.getValue()) {
                Game.screens.setScreen(Game.screens.gameScreen);

            }
            if (Game.screens.titleScreen.optionCursor.optionNum == TitleScreenOptions.QUIT.getValue()) {
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

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("clicked");
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
