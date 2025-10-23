package io.github.spiritstead.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class SoundLibrary {
    private long id;
    private Sound currentSound;
    private final Map<SoundEffect, Sound> soundEffects;
    private final Map<Music, Sound> music;
    private final Map<Music, Sound> sounds;

    public SoundLibrary(Map<SoundEffect, Sound> soundEffects, Map<Music, Sound> music) {
        this.soundEffects = soundEffects;
        this.music = music;
        this.sounds = music;
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
