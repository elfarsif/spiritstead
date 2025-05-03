package io.github.elfarsif.gdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.elfarsif.gdx.GamePanel;

public class OptionMenu {
    private GamePanel gp;
    private BitmapFont font;
    public int subState = 0;
    public int commandNum =0;
    String currentDialogue;
    SpriteBatch batch;

    public OptionMenu(GamePanel gp) {
        this.gp = gp;
        font = new BitmapFont();
        this.font.getData().setScale(2f);
    }

    public void drawOptionsScreen(SpriteBatch batch, String currentDialogue) {
        this.batch = batch;
        this.currentDialogue = currentDialogue;

        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);

        //SUB WINDOW
        int frameWidth = gp.tileSize*10;
        int frameHeight = gp.tileSize*10;
        int frameX = gp.screenWidth/2 - frameWidth/2;
        int frameY = gp.screenHeight/2 - frameHeight/2;

        gp.ui.drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0:
                optionsTop(frameX,frameY, frameHeight);
                break;
            case 1:
                optionsControl(frameX,frameY, frameHeight);
                break;
            case 2:
                optionsExitGame(frameX,frameY, frameHeight);
        }

        gp.keyHandler.enterPressed = false;

    }

    private void optionsTop(int frameX, int frameY, int frameHeight) {
        int textX;
        int textY;

        //TITLE

        String text = "OPTIONS";
        textX = frameX + gp.tileSize;
        textY = frameY + frameHeight - gp.tileSize;
        font.draw(batch, text, textX, textY);

        //FULL SCREEN ON/OFF
      /*  text = "FULL SCREEN";
        textX = frameX+ gp.tileSize;
        textY -= gp.tileSize*2;
        font.draw(batch, text, textX, textY);
        if (commandNum == 0){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
            *//*    if (!gp.fullScreenOn){
                    gp.fullScreenOn = true;
                }
                else{
                    gp.fullScreenOn = false;
                }
*//*
                System.out.println("Full Screen On/Off");
            }
        }*/

        //MUSIC
        text = "MUSIC";
        textY -= gp.tileSize*2;
        font.draw(batch, text, textX, textY);
        if (commandNum == 1){
            font.draw(batch, ">", textX - 30, textY);
        }

        //SOUND EFFECT
        text = "SOUND EFFECT";
        textY -= gp.tileSize;
        font.draw(batch, text, textX, textY);
        if (commandNum == 2){
            font.draw(batch, ">", textX - 30, textY);
        }

        //CONTROL
        text = "CONTROL";
        textY -= gp.tileSize;
        font.draw(batch, text, textX, textY);
        if (commandNum == 3){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
                subState = 1;
                commandNum = 0;
            }
        }

        //Exit
        text = "EXIT";
        textY -= gp.tileSize;
        font.draw(batch, text, textX, textY);
        if (commandNum == 4){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        //BACK
        text = "BACK";
        textY -= gp.tileSize*2;
        font.draw(batch, text, textX, textY);
        if (commandNum == 5){
            font.draw(batch, ">", textX - 30, textY);
        }

        //FULL SCREEN CHECK BOX
        textX = (int) (frameX + gp.tileSize*6.5);
        textY = (int) (frameY + (int)gp.tileSize*2.5);
        Pixmap pixmap = new Pixmap(gp.tileSize, gp.tileSize, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(textX, textY, gp.tileSize / 3, gp.tileSize / 3);

        //Dont initialize it here its super slow compared to initializing it in create and using the same object
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, textX, textY);

        if (gp.fullScreenOn){
            pixmap = new Pixmap(gp.tileSize, gp.tileSize, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.fillRectangle(textX, textY, gp.tileSize / 3, gp.tileSize / 3);
            texture = new Texture(pixmap);
            pixmap.dispose();
            batch.draw(texture, textX, textY);
        }

        //MUSIC SLIDER
        textY -= gp.tileSize;
        pixmap = new Pixmap(gp.tileSize*3, gp.tileSize/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(textX, textY, gp.tileSize*3, gp.tileSize/3);
        texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, textX, textY);
        int volumeWidth = gp.tileSize*3/5 * gp.music.volumeScale;
        pixmap = new Pixmap(volumeWidth, gp.tileSize/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(textX, textY, volumeWidth, gp.tileSize/3);
        texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, textX, textY);

        //SOUND EFFECT SLIDER
        textY -= gp.tileSize;
        pixmap = new Pixmap(gp.tileSize*3, gp.tileSize/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(textX, textY, gp.tileSize*3, gp.tileSize/3);
        texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, textX, textY);
        volumeWidth = gp.tileSize*3/5 * gp.soundEffect.volumeScale;
        pixmap = new Pixmap(volumeWidth, gp.tileSize/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fillRectangle(textX, textY, volumeWidth, gp.tileSize/3);
        texture = new Texture(pixmap);
        pixmap.dispose();
        batch.draw(texture, textX, textY);

        gp.config.saveConfig();
    }

    public void optionsControl(int frameX, int frameY, int frameHeight){
        int textX;
        int textY;

        //TITLE
        String text = "CONTROL";
        textX = gp.ui.getXforCenteredText(text)- frameX;
        textY = frameY + frameHeight - gp.tileSize;
        font.draw(batch, text, textX, textY);

        textX = frameX + gp.tileSize;
        textY -= gp.tileSize;
        font.draw(batch, "Move", textX, textY);
        textY -= gp.tileSize;
        font.draw(batch, "Attack", textX, textY);
        textY -= gp.tileSize;
        font.draw(batch, "Shoot", textX, textY);
        textY -= gp.tileSize;
        font.draw(batch, "Interact", textX, textY);

        textX = frameX + gp.tileSize*6;
        textY = frameY +frameHeight - gp.tileSize*2;
        font.draw(batch, "WASD", textX, textY);
        textY -= gp.tileSize;
        font.draw(batch, "F", textX, textY);
        textY -= gp.tileSize;
        font.draw(batch, "G", textX, textY);
        textY -= gp.tileSize;

        //BACK
        textX = frameX + gp.tileSize;
        textY -= gp.tileSize*2;
        font.draw(batch, "BACK", textX, textY);
        if (commandNum == 0){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }
    }

    private void optionsExitGame(int frameX, int frameY, int frameHeight) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + frameHeight - gp.tileSize*3;

        currentDialogue = "Are you sure you want to exit the game?";
        font.draw(batch, currentDialogue, textX, textY);

        //YES
        String text = "YES";
        textX = gp.ui.getXforCenteredText(text)- frameX;
        textY -= gp.tileSize*2;
        font.draw(batch, text, textX, textY);
        if (commandNum == 0){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }

        //NO
        text = "NO";
        textX = gp.ui.getXforCenteredText(text)- frameX;
        textY -= gp.tileSize;
        font.draw(batch, text, textX, textY);
        if (commandNum == 1){
            font.draw(batch, ">", textX - 30, textY);
            if (gp.keyHandler.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }

    }

}
