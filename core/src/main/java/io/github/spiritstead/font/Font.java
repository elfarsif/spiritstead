package io.github.spiritstead.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Font {
    private BitmapFont bitmapFont;
    private String text;
    private int x, y;

    public Font(String filePath) {
        this.bitmapFont = new BitmapFont();
        initializeBitmapFont(filePath);
    }

    private void initializeBitmapFont(String filePath) {
        //load fonts https://www.youtube.com/watch?v=PE5I_zObJ4I&t=76s
        bitmapFont = new BitmapFont(Gdx.files.internal(filePath));
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bitmapFont.getData().setScale(1f);
    }

    public void draw(SpriteBatch batch, String text, int x, int y) {
        this.bitmapFont.draw(batch, text, x, y);
    }

    public void draw(SpriteBatch batch) {
        this.draw(batch, this.text, this.x, this.y);
    }

    public Font atPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Font withText(String text) {
        this.text = text;
        return this;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }
    public void dispose() {
        bitmapFont.dispose();
    }
}
