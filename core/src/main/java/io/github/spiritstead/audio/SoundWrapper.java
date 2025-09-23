package io.github.spiritstead.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.EnumMap;
import java.util.Map;

public class SoundWrapper {
    private long id;
    private Sound currentSound;
    private final Map<SoundEffect, Sound> soundEffects = new EnumMap<>(SoundEffect.class);
    private final Map<Music, Sound> music = new EnumMap<>(Music.class);

    public SoundWrapper() {
        loadMusic();
        loadSounds();
    }

    private void loadMusic() {
        for (Music music : Music.values()) {
            this.music.put(music, Gdx.audio.newSound(Gdx.files.internal(music.getFileName())));
        }

    }

    private void loadSounds() {
        for (SoundEffect soundEffect : SoundEffect.values()) {
            this.soundEffects.put(soundEffect, Gdx.audio.newSound(Gdx.files.internal(soundEffect.getFileName())));
        }
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
