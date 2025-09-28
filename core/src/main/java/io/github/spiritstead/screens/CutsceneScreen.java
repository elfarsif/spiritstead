package io.github.spiritstead.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.main.GamePanel;

public class CutsceneScreen implements Screen {
    GamePanel gp;
    SpriteBatch batch;
    public int sceneNum;
    private final Cutscene gameIntro;

    public CutsceneScreen(GamePanel gp) {
        this.gp = gp;
        gameIntro = new GameIntro(gp);

    }

    public void draw() {
        gameIntro.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("custscene input");
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
