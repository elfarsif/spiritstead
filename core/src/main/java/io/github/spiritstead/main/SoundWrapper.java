package io.github.spiritstead.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundWrapper {

    long id;
    Sound currentSound;
    Sound sounds[] = new Sound[10];

    public SoundWrapper(){
        sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/theme1.wav"));
        sounds[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
        sounds[2] = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
    }

    public void setFile(int i){
       currentSound = sounds[i];
    }

    public void play(){
        id = currentSound.play(3);

    }

    public void loop(){
        currentSound.setLooping(id,true);

    }

    public void stop(){
        currentSound.stop();

    }


}
