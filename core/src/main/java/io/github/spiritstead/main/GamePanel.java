package io.github.spiritstead.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/*
This class is the main class that handles all aspects of game logic
 */
public class GamePanel extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    //Screen Setting
    final int orginalTileSize =16;
    final int scale = 3;

    final int tileSize = orginalTileSize*scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyH = new KeyHandler();

    //Set player default position
    int playerX = tileSize * 3;
    int playerY = tileSize * 3;
    int playerSpeed = 4;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        Gdx.input.setInputProcessor(keyH);
    }

    @Override
    public void resize(int width, int height) {
        // Resize the default camera viewport to match the new window size
        // This is necessary to ensure that the game scales correctly when the window is resized.
        Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
    }


    @Override
    public void render() {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        update();
        draw();
        batch.end();
    }

    private void update() {
        if (keyH.upPressed){
            playerY += playerSpeed;
        }
        else if (keyH.downPressed){
            playerY -= playerSpeed;
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    private void draw() {
        drawPlayer(playerX, playerY);
    }

    private void drawPlayer(int x, int y) {
        Pixmap pixmap = new Pixmap( tileSize, tileSize, Pixmap.Format.RGBA8888 );
        pixmap.setColor( Color.PINK);
        pixmap.fillRectangle(0,0, tileSize, tileSize);
        image = new Texture( pixmap );
        pixmap.dispose();

        batch.draw(image, playerX, playerY);
    }


    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
