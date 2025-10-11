package io.github.spiritstead.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private final String value;
    private final LetterByLetterEffect letterByLetterEffect;

    public Text(String value, LetterByLetterEffect letterByLetterEffect) {
        this.value = value;
        this.letterByLetterEffect = letterByLetterEffect;
        this.letterByLetterEffect.setText(value);
    }

    public void draw(SpriteBatch batch, int x, int y) {
        letterByLetterEffect.draw(x, y);

    }

}
