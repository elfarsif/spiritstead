package io.github.spiritstead.screens;

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
