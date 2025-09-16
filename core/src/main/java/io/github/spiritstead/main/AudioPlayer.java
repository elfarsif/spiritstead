package io.github.spiritstead.main;

public class AudioPlayer {
    SoundWrapper music;
    SoundWrapper se;

    public AudioPlayer() {
        this.music = new SoundWrapper();
        this.se = new SoundWrapper();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
