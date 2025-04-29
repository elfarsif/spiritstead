package io.github.elfarsif.cutscene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.elfarsif.gdx.GamePanel;

public class GameIntroCutscene {
    //System
    GamePanel gp;
    SpriteBatch batch;

    BitmapFont font;
    public int maxSceneNumber=2;
    public Sprite blackBackground;
    public Sprite firstCutsceneImage;
    public Sprite secondCutsceneImage;


    String displayedText = "";
    int charIndex=0;
    String combinedText = "";
    public int scenePart=0;


    public GameIntroCutscene(GamePanel gp){
        this.gp = gp;
        generateBlackBackgroundSprite();
        loadCutsceneAssets();
    }

    public void start(SpriteBatch batch) {
        this.batch= batch;

        //Go to next scene part
        if (gp.keyHandler.spacePressed){
            scenePart++;
            //Reset text
            charIndex = 0;
            combinedText = "";
            displayedText = "";
            //TODO:Bug for pagination in dialogue state
            gp.keyHandler.spacePressed = false;

        }

        switch (scenePart) {
            case 0:
                firstCutsceneImage();
                break;
            case 1:
                secondCutsceneImage();
                break;

        }

        //Second cutsceneImage


    }

    private void firstCutsceneImage() {
        batch.draw(blackBackground, 0, 0,blackBackground.getWidth(), blackBackground.getHeight());

        //addFirstCutsceneImage
        int imageXposition = (int) (gp.screenWidth/2-(firstCutsceneImage.getWidth()/2));
        int imageYposition = (int) (gp.screenHeight-(firstCutsceneImage.getHeight()+gp.tileSize));
        batch.draw(firstCutsceneImage, imageXposition, imageYposition, firstCutsceneImage.getWidth(), firstCutsceneImage.getHeight());

        addFirstCutsceneText(imageYposition);

    }

    private void loadCutsceneAssets() {
        try {
            firstCutsceneImage = new Sprite(new Texture("cutscene/intro-cutscene-1.png"));
            firstCutsceneImage.setSize(gp.screenWidth/2, gp.screenHeight/2);

            secondCutsceneImage = new Sprite(new Texture("cutscene/intro-cutscene-2.png"));
            secondCutsceneImage.setSize(gp.screenWidth/2, gp.screenHeight/2);
        } catch (Exception e) {
            throw new RuntimeException("Error reading image for mushroom:" + e);
        }
    }

    private void generateBlackBackgroundSprite(){
        // Create a 1x1 black pixel texture
        Texture blackTexture;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        blackTexture = new Texture(pixmap);
        pixmap.dispose(); // No longer needed after texture creation

        blackBackground = new Sprite(blackTexture);
        blackBackground.setSize(gp.screenWidth, gp.screenHeight);


    }

    private void addFirstCutsceneText(int imageYposition) {
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        String text = "Welcome to our place of residence we are just adding words to see the cutoff of the area";


        //Letter by letter effect
        char characters[] = text.toCharArray();

        if (charIndex < characters.length) {
            String s = String.valueOf(characters[charIndex]);
            combinedText += s;
            displayedText = combinedText;
            gp.playSoundEffect(4);
            charIndex++;
        }
        font.draw(batch, displayedText,gp.ui.getXforCenteredText(text) ,imageYposition-gp.tileSize);

    }

    private void secondCutsceneImage() {

        batch.draw(blackBackground, 0, 0, blackBackground.getWidth(), blackBackground.getHeight());

        //addFirstCutsceneImage
        int imageXposition = (int) (gp.screenWidth / 2 - (secondCutsceneImage.getWidth() / 2));
        int imageYposition = (int) (gp.screenHeight - (secondCutsceneImage.getHeight() + gp.tileSize));
        batch.draw(secondCutsceneImage, imageXposition, imageYposition, secondCutsceneImage.getWidth(), secondCutsceneImage.getHeight());

        addSecondCutsceneText(imageYposition);

    }

    private void addSecondCutsceneText(int imageYposition) {
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        String text = "This is the second cutscene text";


        //Letter by letter effect
        char characters[] = text.toCharArray();

        if (charIndex < characters.length) {
            String s = String.valueOf(characters[charIndex]);
            combinedText += s;
            displayedText = combinedText;
            gp.playSoundEffect(4);
            charIndex++;
        }
        font.draw(batch, displayedText,gp.ui.getXforCenteredText(text) ,imageYposition-gp.tileSize);

    }



}
