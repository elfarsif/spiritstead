package io.github.spiritstead.cutscene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;

public class LetterByLetterEffect {
    private GamePanel gp;
    private SpriteBatch batch;
    private String text;
    private FrameGate frameGate;
    private int charIndex = 0;
    private String combinedText = "";
    private String displayedText = "";
    private Font font;
    public InputGate inputGate;
    private char[] characters;
    private String s;

    public LetterByLetterEffect(GamePanel gp, Font font) {
        this.gp = gp;
        this.batch = gp.batch;
        this.font = font;
        this.inputGate = gp.keyH.inputGate;
        this.frameGate = new FrameGate(2);
    }

    public void draw(float x, float y) {
        inputGate.close();
        this.characters = text.toCharArray();
        if (frameGate.tick()) {
            if (charIndex < characters.length) {
                s = String.valueOf(characters[charIndex]);
                combinedText += s;
                displayedText = combinedText;
//                gp.audioPlayer.playSE(SoundEffect.DIALOGUE);
                charIndex++;
            }
            frameGate.reset();
        }

        this.font.getBitmapFont().draw(batch, displayedText, x, y);

        if (allTextIsDisplayed()) {
            inputGate.open();
        }
    }

    public void reset() {
        displayedText = "";
        charIndex = 0;
        combinedText = "";
    }

    private boolean allTextIsDisplayed() {
        return displayedText.length() == text.length();
    }

    public void setText(String text) {
        this.text = text;
    }
}
