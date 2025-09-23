package io.github.spiritstead.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.EnumMap;
import java.util.Map;

public class SoundWrapper {
    private long id;
    private Sound currentSound;
    private Sound sounds[] = new Sound[10];
    private final Map<SoundEffect, Sound> soundEffects = new EnumMap<>(SoundEffect.class);
    private final Map<Music, Sound> music = new EnumMap<>(Music.class);

    public SoundWrapper() {
        this.sounds[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/theme1.wav"));
        this.sounds[2] = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));
        this.sounds[3] = Gdx.audio.newSound(Gdx.files.internal("sounds/dialogue.wav"));
        this.soundEffects.put(SoundEffect.COIN, Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav")));
        this.soundEffects.put(SoundEffect.POWERUP, Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav")));
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

    public void setFile(SoundEffect soundEffect) {
        this.currentSound = this.soundEffects.get(soundEffect);
    }

    public void setFile(Music music) {
        this.currentSound = this.music.get(music);
    }
}
