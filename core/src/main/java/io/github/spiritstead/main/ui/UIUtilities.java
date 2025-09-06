package io.github.spiritstead.main.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.spiritstead.main.GamePanel;

public class UIUtilities {

    static public BitmapFont initializeFont(BitmapFont font, String fileName) {
        //load fonts https://www.youtube.com/watch?v=PE5I_zObJ4I&t=76s
        font = new BitmapFont(Gdx.files.internal(fileName));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        font.getData().setScale(1f);
        return font;
    }

     static public float getXforCenteredText(BitmapFont font, GlyphLayout layout,String text, GamePanel gp){
        layout.setText(font,text);
        return gp.screenWidth/2 - layout.width/2;

    }
}
