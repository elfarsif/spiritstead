package io.github.spiritstead.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.object.Key;

public class UI {

    GamePanel gp;
    BitmapFont font;
    Sprite keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp){
        this.gp = gp;
        loadKeyImage();
        loadFont();
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    private void loadKeyImage() {
        Key key = new Key();
        keyImage = key.image;
    }

    private void loadFont() {
        font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        font.getData().setScale(1f);
    }

    public void draw(SpriteBatch batch){

        batch.draw(keyImage,10,gp.screenHeight-gp.tileSize,gp.tileSize,gp.tileSize);
        font.draw(batch,Integer.toString(gp.player.hasKey),2*gp.tileSize,gp.screenHeight-10);

        //Message
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

    public void dispose(){
        font.dispose();
    }
}
