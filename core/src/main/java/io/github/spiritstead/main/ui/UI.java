package io.github.spiritstead.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.GameState;
import io.github.spiritstead.object.Key;

/**
 * This class manages the different UI elements in different states
 */
public class UI {

    GamePanel gp;
    SpriteBatch batch;

    //Screens
    public TitleScreenUI titleScreenUI;
    public GameStateUI gameStateUI;

    public UI(GamePanel gp){
        this.gp = gp;
        this.titleScreenUI = new TitleScreenUI(this.gp);
        this.gameStateUI = new GameStateUI(this.gp);

    }

    public void draw(SpriteBatch batch){
        this.batch = batch;

        if (gp.gameState == GameState.TITLESTATE){
            titleScreenUI.draw(batch);
        }

        if (gp.gameState == GameState.PLAYSTATE){
            gameStateUI.draw(batch);
        }

    }

    public void dispose(){
        gameStateUI.dispose();
        titleScreenUI.dispose();
    }
}
