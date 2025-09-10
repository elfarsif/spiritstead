package io.github.spiritstead.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.object.Key;

public class GameStateUI {

    GamePanel gp;
    BitmapFont font;
    Sprite keyImage;
    SpriteBatch batch;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public GameStateUI(GamePanel gp){
        this.gp = gp;
        loadKeyImage();
        font = UIUtilities.initializeFont(font,"fonts/maruMonicaBold.fnt");

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    private void loadKeyImage() {
        Key key = new Key();
        keyImage = key.image;
    }

    private void drawMessages() {
        if (messageOn){
            float scale= font.getScaleX();
            font.getData().setScale(0.75f);
            font.draw(batch,message,gp.tileSize,gp.screenHeight-(3*gp.tileSize));
            font.getData().setScale(scale);

            messageCounter++;

            if (messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    private void drawKeyInventory() {
        batch.draw(keyImage,10,gp.screenHeight-gp.tileSize,gp.tileSize,gp.tileSize);
        font.draw(batch,Integer.toString(gp.player.hasKey),2*gp.tileSize,gp.screenHeight-10);

    }


    public void draw(SpriteBatch batch) {
        this.batch = batch;
        drawKeyInventory();
        drawMessages();
        if (gp.keyH.tPressed){
            int x = 10;
            int y = 40;
            int lineHeight = 30;
            font.draw(batch, "Player X: " + gp.player.worldX, x, y);
            y += lineHeight;
            font.draw(batch, "Player Y: " + gp.player.worldY, x, y);
            y += lineHeight;
            font.draw(batch, "Player Col: " + (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize, x, y);
            y += lineHeight;
            font.draw(batch, "Player Row: " + (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize, x, y);
        }
    }

    public void dispose() {
        font.dispose();
    }
}
