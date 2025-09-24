package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.ScreenSetting;

/**
 * This class adds \n to string to wrap at a certain width
 */
public class TextWrapper {
    String text;
    public String wrappedText;
    int width;

    GlyphLayout layout = new GlyphLayout();
    StringBuilder wrappedTextStringBuilder = new StringBuilder();
    Font font;

    public TextWrapper(Font font) {
        this.font = font;
    }

    public TextWrapper(Font font, int width) {
        this.font = font;
        this.width = width;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String wrap() {
        String originalText = this.text;

        //TODO: maxwidth should be tilesize, make tile size a global final variable
        float maxWidth = width + ScreenSetting.TILE_SIZE * 2; // or set manually
        String[] words = originalText.split(" ");
        String line = "";

        for (String word : words) {
            String testLine = line.isEmpty() ? word : line + " " + word;
            layout.setText(font.getBitmapFont(), testLine);

            if (layout.width > maxWidth) {
                wrappedTextStringBuilder.append(line).append("\n");
                line = word; // Start new line with the current word
            } else {
                line = testLine;
            }
        }
        wrappedTextStringBuilder.append(line); // append the last line

        String finalText = wrappedTextStringBuilder.toString();
        wrappedTextStringBuilder.setLength(0);
        wrappedText = finalText;
        return finalText;
    }

}
