package io.github.spiritstead.audio;

import com.badlogic.gdx.Gdx;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AudioPlayer {
    private static AudioPlayer instance;
    private SoundLibrary music;
    private SoundLibrary se;

    public static AudioPlayer getInstance() {
        if (instance == null) {
            instance = new AudioPlayer(
                    new SoundLibrary(
                            Arrays.stream(SoundEffect.values())
                                    .collect(Collectors.toMap(
                                            soundEffect -> soundEffect,
                                            soundEffect -> Gdx.audio.newSound(Gdx.files.internal(soundEffect.getFileName()))
                                    )),
                            Arrays.stream(Music.values())
                                    .collect(Collectors.toMap(
                                            music -> music,
                                            music -> Gdx.audio.newSound(Gdx.files.internal(music.getFileName()))
                                    ))
                    )
            );

        }
        return instance;
    }

    private AudioPlayer(SoundLibrary soundLibraryEffect) {
        this.music = soundLibraryEffect;
        this.se = soundLibraryEffect;
    }

    public void playMusic(Music music) {
        this.music.setFile(music);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(SoundEffect soundEffect) {
        this.se.setFile(soundEffect);
        this.se.play();
    }

}
