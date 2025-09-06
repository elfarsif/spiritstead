package io.github.spiritstead.cutscene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.gameIntro.GameIntro;
import io.github.spiritstead.main.GamePanel;

public class CutsceneManager {
    GamePanel gp;
    SpriteBatch batch;
    public int sceneNum;
    private final
    Cutscene gameIntro;

    public CutsceneManager(GamePanel gp){
        gameIntro = new GameIntro(gp);

    }
    public void draw(SpriteBatch batch){
        gameIntro.draw(batch);

    }
}
