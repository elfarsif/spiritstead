package io.github.spiritstead.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundWrapper {
    private long id;
    private Sound currentSound;
    private Sound sounds[] = new Sound[10];

    public SoundWrapper() {
        this.sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/theme1.wav"));
        this.sounds[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
        this.sounds[2] = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
        this.sounds[3] = Gdx.audio.newSound(Gdx.files.internal("sounds/dialogue.wav"));
    }

    public void setFile(int i) {
        this.currentSound = this.sounds[i];
    }

    public void play() {
        this.id = this.currentSound.play(3);

    }

    public void loop() {
        this.currentSound.setLooping(this.id, true);

    }

    public void stop() {
        this.currentSound.stop();

    }

}
