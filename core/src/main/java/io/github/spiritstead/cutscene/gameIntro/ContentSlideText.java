package io.github.spiritstead.cutscene.gameIntro;

import io.github.spiritstead.font.Font;
import io.github.spiritstead.main.FrameGate;
import io.github.spiritstead.main.GamePanel;
import io.github.spiritstead.main.ScreenSetting;

import java.util.ArrayList;

public class ContentSlideText {
    GamePanel gp;
    GameIntro gameIntro;
    ContentSlide contentSlide;
    ArrayList<String> texts;
    Font font;
    String displayedText = "";
    int charIndex = 0;
    String combinedText = "";
    private TextWrapper textWrapper;
    public int textCounter = 0;
    private FrameGate frameGate;

    public ContentSlideText(GamePanel gp, GameIntro gameIntro, ContentSlide contentSlide, ArrayList<String> text) {
        this.gp = gp;
        this.texts = text;
        this.gameIntro = gameIntro;
        this.contentSlide = contentSlide;
        this.frameGate = new FrameGate(2);
        font = new Font("fonts/maruMonica.fnt");
        this.textWrapper = new TextWrapper(font);
    }

    private void displayText(int counter) {
        String text = texts.get(counter);
        textWrapper.setOriginalText(text);
        textWrapper.setWidth((int) contentSlide.image1.getWidth());
        textWrapper.wrap();
        firstImageText(text);
    }

    private void firstImageText(String text) {
        text = textWrapper.text;

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

        font.getBitmapFont().draw(gp.batch, displayedText, contentSlide.image1X - ScreenSetting.TILE_SIZE, contentSlide.image1Y - ScreenSetting.TILE_SIZE);
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
