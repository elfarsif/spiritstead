package io.github.spiritstead.audio;

public class AudioPlayer {
    private SoundWrapper music;
    private SoundWrapper se;

    public AudioPlayer() {
        this.music = new SoundWrapper();
        this.se = new SoundWrapper();
    }

    public void playMusic(int i) {
        this.music.setFile(i);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(int i) {
        this.se.setFile(i);
        this.se.play();
    }
}
