package io.github.elfarsif.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public class SoundWrapper {

    long id;
    Sound currentSound;
    Sound sounds[] = new Sound[10];
    public int maxVolumeScale =5;
    public int volumeScale = 3; // 0 to 5
    float volume;

    public SoundWrapper(){
        sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/acoustic-lofi.wav"));
        sounds[1] = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/chopping-wood.wav"));
        sounds[2] = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/inventory-cursor.wav"));
        sounds[3] = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/sword-sound.wav"));
        sounds[4] = (Sound) Gdx.audio.newSound(Gdx.files.internal("sounds/dialogue.wav"));

        currentSound = sounds[0];
        checkVolume();

    }

    public void checkVolume(){

        switch (volumeScale) {
            case 0:
                volume = 0.0f;
                currentSound.setVolume(id, volume);
                break;
            case 1:
                volume = 0.25f;
                currentSound.setVolume(id, volume);
                break;
            case 2:
                volume = 0.4f;
                currentSound.setVolume(id, volume);
                break;
            case 3:
                volume = 0.6f;
                currentSound.setVolume(id, volume);
                break;
            case 4:
                volume = 0.8f;
                currentSound.setVolume(id, volume);
                break;
            case 5:
                volume = 1.0f;
                currentSound.setVolume(id, volume);
                break;
            default:
                volume = 0.6f;
                currentSound.setVolume(id, volume);
        }
    }

    public void setFile(int i){
        currentSound = sounds[i];
    }

    public void play(){
        id = currentSound.play(volume);
    }

    public void stop(){
        currentSound.stop();
    }

    public void loop(){
        currentSound.setLooping(id,true);
    }

}
