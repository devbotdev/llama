package Variables;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

   private Clip clip;
   private final URL[] soundUrl = new URL[1];
   private AudioInputStream ais;
   private FloatControl gainControl;
   private long clipTime;

    public Sound() {
        try {
            soundUrl[0] = new URL("file:///C:\\Users\\f\\IdeaProjects\\llama\\src\\game_resources\\sound\\shepherd.wav");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFile(byte i, float volume) {
        System.out.println(soundUrl.length);
        if (i >= soundUrl.length) return;
        System.out.println(soundUrl.length);
        try {
            ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        if (clip != null) clip.start();
    }

    public void pause() {
        if (clip != null) {
            clipTime = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null) {
            clip.setMicrosecondPosition(clipTime);

            clip.start();
        }
    }

    public void loop() {
        if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) clip.stop();
    }
}
