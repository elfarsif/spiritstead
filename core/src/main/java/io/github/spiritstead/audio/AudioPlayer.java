package io.github.spiritstead.audio;

import java.util.Map;

public class AudioPlayer {
    private SoundWrapper music;
    private SoundWrapper se;

    public AudioPlayer() {
        this.music = new SoundWrapper();
        this.se = new SoundWrapper();
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
