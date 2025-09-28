package io.github.spiritstead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class DialogueScreen implements Screen, InputProcessor {
    GameScreen gameScreen;

    public DialogueScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void draw() {
        gameScreen.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("dialogue");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
