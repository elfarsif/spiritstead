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
    FadeBlack fadeBlack;
    ContentSlideText contentSlideText;

    public ContentSlide(GamePanel gp, GameIntro gameIntro, String imageFileName, ArrayList<String> texts) {
        this.gp = gp;
        this.batch = gp.batch;
        this.gameIntro = gameIntro;
        this.fadeBlack = new FadeBlack(gp);
        this.contentSlideText = new ContentSlideText(gp, gameIntro, this, texts);

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

    public void draw() {

        batch.draw(image1, image1X, image1Y, image1.getWidth(), image1.getHeight());
        contentSlideText.draw();
        fadeBlack.draw();

    }

}
