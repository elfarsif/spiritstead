package io.github.spiritstead.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class DialogueScreen implements Screen {
    GameScreen gameScreen;

    public DialogueScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void draw() {
        gameScreen.draw();
    }

}
