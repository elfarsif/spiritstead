package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.font.Font;

/**
 * This class adds \n to string to wrap at a certain width
 */
public class TextWrapper {
    String originalText;
    String text;

    int width;

    GlyphLayout layout = new GlyphLayout();
    StringBuilder wrappedText = new StringBuilder();
    Font font;

    public TextWrapper(Font font) {
        this.font = font;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String wrap() {
        String originalText = this.originalText;

        //TODO: maxwidth should be tilesize, make tile size a global final variable
        float maxWidth = width + 48 * 2; // or set manually
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
        text = finalText;
        return finalText;
    }

}
