package io.github.spiritstead.cutscene.gameIntro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.spiritstead.cutscene.Cutscene;
import io.github.spiritstead.main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;

public class GameIntro implements Cutscene {
    GamePanel gp;
    private SpriteBatch batch;
    int slideCounter =0;
    ArrayList<Slide> slides = new ArrayList<>();


    public GameIntro(GamePanel gp){
        this.gp = gp;

        slides.add(new Slide(gp,"intro/spiritstead.png",new ArrayList<>(Arrays.asList("text 1.1","text 1.2"))));
        slides.add(new Slide(gp,"intro/introSlide.png",new ArrayList<>(Arrays.asList("text 2.1","text 2.2","text 2.3"))));

    }


    @Override
    public void draw(SpriteBatch batch) {
        this.batch = batch;

        if (gp.keyH.spacePressed){

            if (slides.get(0).textCounter < slides.get(0).texts.size()-1){
                slides.get(0).textCounter++;
                slides.get(0).displayedText ="";
                slides.get(0).charIndex = 0;
                slides.get(0).combinedText ="";
            }else {
                slideCounter++;
                if (!slides.isEmpty()){
                    slides.remove(0);
                }
            }

            gp.keyH.spacePressed =false;
        }

        if (!slides.isEmpty()){
            slides.get(0).draw(batch);
        }





    }

}
