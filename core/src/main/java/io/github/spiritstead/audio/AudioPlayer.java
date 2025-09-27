package io.github.spiritstead.audio;

public class AudioPlayer {
    private Sound music;
    private Sound se;

    public AudioPlayer() {
        this.music = new Sound();
        this.se = new Sound();
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
