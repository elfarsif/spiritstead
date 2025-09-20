package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;

public class ContentSlideText {
    GamePanel gp;
    GameIntro gameIntro;
    ContentSlide contentSlide;
    ArrayList<String> texts;
    Font font;
    GlyphLayout layout = new GlyphLayout();
    StringBuilder wrappedText = new StringBuilder();
    String displayedText = "";
    int charIndex = 0;
    String combinedText = "";
    public int textCounter = 0;
    private FrameGate frameGate;
    private int speedCounter = 0;

    public ContentSlideText(GamePanel gp, GameIntro gameIntro, ContentSlide contentSlide, ArrayList<String> text) {
        this.gp = gp;
        this.texts = text;
        this.gameIntro = gameIntro;
        this.contentSlide = contentSlide;
        this.frameGate = new FrameGate(2);
        font = new Font("fonts/maruMonica.fnt");
    }

    private void displayText(int counter) {
        String text = texts.get(counter);
        firstImageText(text);
    }

    private void firstImageText(String text) {
        text = wrapTextToSizeWithLineBreaks(text, (int) contentSlide.image1.getWidth());
        //Letter by letter effect
        char characters[] = text.toCharArray();
        if (frameGate.tick()) {

            if (charIndex < characters.length) {
                String s = String.valueOf(characters[charIndex]);
                combinedText += s;
                displayedText = combinedText;
//                gp.system.audioPlayer.playSE(3);
                charIndex++;
            }

            frameGate.reset();
        }

        font.getBitmapFont().draw(gp.batch, displayedText, contentSlide.image1X - gp.sSetting.tileSize, contentSlide.image1Y - gp.sSetting.tileSize);
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
        displayText(textCounter);
    }
}
