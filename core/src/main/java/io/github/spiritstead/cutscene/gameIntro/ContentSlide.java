package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.FadeBlack;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.ui.UIUtilities;

import java.util.ArrayList;

public class ContentSlide implements Slide {
    GamePanel gp;
    GameIntro gameIntro;
    Sprite image1;
    float image1X, image1Y;
    ArrayList<String> texts;
    private SpriteBatch batch;
    StringBuilder wrappedText = new StringBuilder();
    GlyphLayout layout = new GlyphLayout();
    FadeBlack fadeBlack;
    Font font;

    public int textCounter = 0;
    public boolean completed = false;

    String displayedText = "";
    int charIndex = 0;
    String combinedText = "";

    public ContentSlide(GamePanel gp, GameIntro gameIntro, String imageFileName, ArrayList<String> texts) {
        this.gp = gp;
        this.batch = gp.batch;
        this.gameIntro = gameIntro;
        this.fadeBlack = new FadeBlack(gp);
        font = new Font("fonts/MaruMonica.fnt");

        setImage(imageFileName);
        this.texts = texts;
    }

    public void setImage(String fileName) {
        try {
            image1 = new Sprite(new Texture(fileName));
            image1.setSize(gp.sSetting.tileSize * 10, gp.sSetting.tileSize * 6);

            image1X = gp.sSetting.screenWidth / 2 - image1.getWidth() / 2;
            image1Y = gp.sSetting.screenHeight / 2 - image1.getHeight() / 4;
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void setText(ArrayList<String> texts) {
        this.texts = texts;
    }

    private void displayText(int counter) {
        String text = texts.get(counter);
        firstImageText(text);
    }

    private void firstImageText(String text) {
        text = wrapTextToSizeWithLineBreaks(text, (int) image1.getWidth());
        //Letter by letter effect
        char characters[] = text.toCharArray();

        if (charIndex < characters.length) {
            String s = String.valueOf(characters[charIndex]);
            combinedText += s;
            displayedText = combinedText;
//            gp.playSoundEffect(4);
            charIndex++;
        }
        font.getBitmapFont().draw(batch, displayedText, image1X - gp.sSetting.tileSize, image1Y - gp.sSetting.tileSize);
    }

    private String wrapTextToSizeWithLineBreaks(String text, int imageWidth) {
        String originalText = text;

        float maxWidth = imageWidth + gp.sSetting.tileSize * 2; // or set manually
        String[] words = originalText.split(" ");
        String line = "";

        for (String word : words) {
            String testLine = line.isEmpty() ? word : line + " " + word;
            layout.setText(font.getBitmapFont(), testLine);

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

    public void draw() {

        if (gp.system.keyH.spacePressed) {

            if (textCounter < texts.size() - 1) {
                textCounter += 1;
                displayedText = "";
                charIndex = 0;
                combinedText = "";
            } else {
                gameIntro.slideCounter++;
            }

            gp.system.keyH.spacePressed = false;
        }
        batch.draw(image1, image1X, image1Y, image1.getWidth(), image1.getHeight());
        displayText(textCounter);
        fadeBlack.draw();

    }

}
