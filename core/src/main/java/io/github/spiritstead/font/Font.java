package io.github.spiritstead.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font {
    private BitmapFont bitmapFont;

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

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }

}
