package io.github.elfarsif.environment;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.elfarsif.gdx.GamePanel;
public class Lighting {
    GamePanel gp;
    Texture darknessTexture;
    int lightedCircleSize;
    int dayCounter;
    float filterAlpha = 0f;

    final int day =0;
    final int dusk =1;
    final int night =2;
    final int dawn =3;
    int dayState = day;


    public Lighting(GamePanel gp, int lightedCircleSize) {
        this.gp = gp;
        this.lightedCircleSize = lightedCircleSize;
        setLightingTexture();  // create initial texture
    }

    public void setLightingTexture() {
        if (darknessTexture != null) darknessTexture.dispose(); // dispose old

        int width = gp.screenWidth;
        int height = gp.screenHeight;

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Fill the entire screen with black (95% opacity)
        pixmap.setColor(0f, 0f, 0.4f, this.filterAlpha);
        pixmap.fill();

        // Get center of light
        int centerX = gp.player.screenX + gp.tileSize / 2;
        int centerY = gp.player.screenY + gp.tileSize / 2;

        /*// Cut a transparent circle in the black overlay
        pixmap.setBlending(Pixmap.Blending.None); // disable blending for alpha punch-through
        pixmap.setColor(0f, 0f, 0f, 0f); // fully transparent
        pixmap.fillCircle(centerX, centerY, lightedCircleSize / 2);*/

        darknessTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void update(){

        // Update the day state
        if (dayState == day){
            dayCounter++;
            if(dayCounter >300){
                dayState = dusk;
                dayCounter = 0;
            }
        }
        // Dusk
        if (dayState == dusk){
            filterAlpha += 0.001f;


            if (filterAlpha >0.5f){
                filterAlpha = 0.5f;
                dayState = night;
            }
        }
        // Night
        if (dayState == night){
            dayCounter++;
            if(dayCounter >300){
                dayState = dawn;
                dayCounter = 0;
            }
        }
        // Dawn
        if (dayState == dawn){
            filterAlpha -= 0.001f;
            if (filterAlpha <0f){
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        setLightingTexture();
        batch.setBlendFunction(com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA, com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.draw(darknessTexture, 0, 0);

        //DEBUG
        String situation = "";
        switch (dayState) {
            case day:
                situation = "Day";
                break;
            case dusk:
                situation = "Dusk";
                break;
            case night:
                situation = "Night";
                break;
            case dawn:
                situation = "Dawn";
                break;
        }
        gp.font.draw(batch, "Day State: " + situation +" "+filterAlpha+" day counter"+dayCounter, 10, 40);
    }

    public void dispose() {
        if (darknessTexture != null) {
            darknessTexture.dispose();
        }
    }
}
