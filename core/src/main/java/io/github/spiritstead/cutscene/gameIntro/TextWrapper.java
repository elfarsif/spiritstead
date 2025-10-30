package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.ScreenSetting;

/**
 * This class adds \n to string to wrap at a certain width
 */
public final class TextWrapper {
    private String wrappedText;

    private final GlyphLayout layout = new GlyphLayout();
    private final StringBuilder wrappedTextStringBuilder = new StringBuilder();
    private final Font font;

    public TextWrapper(Font font) {
        this.font = font;
    }

    public String wrap(String text, int width) {
        String originalText = text;

        //TODO: maxwidth should be tilesize, make tile size a global final variable
        float maxWidth = width + ScreenSetting.TILE_SIZE * 2; // or set manually
        String[] words = originalText.split(" ");
        String line = "";

        for (String word : words) {
            String testLine = line.isEmpty() ? word:line + " " + word;
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

    public String getText() { return this.wrappedText; }
}
