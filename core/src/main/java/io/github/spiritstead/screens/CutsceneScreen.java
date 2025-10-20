package io.github.spiritstead.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.main.GamePanel;

public class CutsceneScreen implements Screen {
    private final Cutscene gameIntro;

    public CutsceneScreen(GamePanel gp) { gameIntro = new GameIntro(gp); }

    public void draw() { gameIntro.draw(); }

}
