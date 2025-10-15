package io.github.spiritstead.audio;

public class AudioPlayer {
    private static AudioPlayer instance;
    private Sound music;
    private Sound se;

    public static AudioPlayer getInstance() {
        if (instance == null) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    private AudioPlayer() {
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
