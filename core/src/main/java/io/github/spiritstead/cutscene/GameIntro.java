package io.github.spiritstead.cutscene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ui.UIUtilities;

public class GameIntro implements Cutscene{
    Texture test = new Texture("player/down1.png");
    BitmapFont font;
    GamePanel gp;
    Sprite image1;
    float image1X,image1Y;
    public int introSceneNum = 0;
    String text1 = "In one of the small nooks on the maps of our world lied a town full of characters and adventures.";
    private SpriteBatch batch;
    StringBuilder wrappedText = new StringBuilder();
    GlyphLayout layout = new GlyphLayout();

    String displayText= "";

    public GameIntro(GamePanel gp){
        this.gp = gp;
        font = UIUtilities.initializeFont(font,"fonts/maruMonica.fnt");
        loadFirstCutsceneImage();
    }

    private void loadFirstCutsceneImage() {
        try {
            image1 = new Sprite(new Texture("intro/spiritstead.png"));
            image1.setSize(gp.tileSize*10,gp.tileSize*6);

            image1X = gp.screenWidth/2 - image1.getWidth()/2;
            image1Y = gp.screenHeight/2-image1.getHeight()/4;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void firstImageText(){
        text1 = addNewLinesToText(text1,(int)image1.getWidth());
        font.draw(batch,text1,image1X-gp.tileSize,image1Y-gp.tileSize);
    }

    private String addNewLinesToText(String text, int imageWidth){
        String originalText = text;

        float maxWidth = imageWidth+gp.tileSize*2; // or set manually
        String[] words = originalText.split(" ");
        String line = "";

        for (String word : words) {
            String testLine = line.isEmpty() ? word : line + " " + word;
            layout.setText(font, testLine);

            if (layout.width > maxWidth) {
                wrappedText.append(line).append("\n");
                line = word; // Start new line with the current word
            } else {
                line = testLine;
            }
        }
        wrappedText.append(line); // append the last line

        String finalText = wrappedText.toString();
        wrappedText.setLength(0);
        return finalText;
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.batch = batch;


        batch.draw(image1,image1X ,image1Y,image1.getWidth(),image1.getHeight());
        firstImageText();

    }
}
