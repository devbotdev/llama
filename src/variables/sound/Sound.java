package variables.sound;

import variables.Vars;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

   private Clip clipFX;
   private final URL[] soundUrlFX = new URL[1];
   private AudioInputStream aisFX;
   private FloatControl gainControlFX;
   private long clipTimeFX;
   private static float fxVolume;

    private Clip clipMusic;
    private final URL[] soundUrlMusic = new URL[1];
    private AudioInputStream aisMusic;
    private FloatControl gainControlMusic;
    private long clipTimeMusic;
    private static float musicVolume;

    public Sound() {
        fxVolume = 0;
        musicVolume = 0;

//        try {
//            soundUrlFX[0] = new URL("file:///" + Vars.directory + "\\game_resources\\sound\\shepherd.wav");
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }

        try {
            soundUrlMusic[0] = new URL("file:///" + Vars.directory + "\\game_resources\\sound\\shepherd.wav");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPercentage(double decibels) {
       return Math.pow(10, decibels/10) * 100;
    }

    public double getDB(double percentage) {
        return 20 * Math.log(percentage / 100);
    }

    public void setFile(byte s, int i, float volume) {
       if (s == SoundType.SOUND_FX) {
           if (i >= soundUrlFX.length) return;
           try {
               aisFX = AudioSystem.getAudioInputStream(soundUrlFX[i]);
               clipFX = AudioSystem.getClip();
               clipFX.open(aisFX);
               gainControlFX = (FloatControl) clipFX.getControl(FloatControl.Type.MASTER_GAIN);
               gainControlFX.setValue(volume);
           } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
               throw new RuntimeException(e);
           }

       } else if (s == SoundType.MUSIC) {
           if (i >= soundUrlMusic.length) return;
           try {
               aisMusic = AudioSystem.getAudioInputStream(soundUrlMusic[i]);
               clipMusic = AudioSystem.getClip();
               clipMusic.open(aisMusic);
               gainControlMusic = (FloatControl) clipMusic.getControl(FloatControl.Type.MASTER_GAIN);
               gainControlMusic.setValue(volume);
           } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
               throw new RuntimeException(e);
           }
       }
    }

    public void play(byte i) {
       if (i == SoundType.MUSIC) {
           if (clipMusic != null) clipMusic.start();
       } else if (i == SoundType.SOUND_FX) {
           if (clipFX != null) clipFX.start();
       }
    }

    public void pause(byte i) {
       if (i == SoundType.MUSIC) {
           if (clipMusic != null) {
               clipTimeMusic = clipMusic.getMicrosecondPosition();
               clipMusic.stop();
           }
       } else if (i == SoundType.SOUND_FX) {
           if (clipFX != null) {
               clipTimeFX = clipFX.getMicrosecondPosition();
               clipFX.stop();
           }
       }
    }

    public void resume(byte i) {
       if (i == SoundType.MUSIC) {
           if (clipMusic != null) {
               clipMusic.setMicrosecondPosition(clipTimeMusic);

               clipMusic.start();
           }
       } else if (i == SoundType.SOUND_FX) {
           if (clipFX != null) {
               clipFX.setMicrosecondPosition(clipTimeFX);

               clipFX.start();
           }
       }
    }

    public void loop(byte i) {
       if (i == SoundType.MUSIC) {
           if (clipMusic != null) clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
       } else if (i == SoundType.SOUND_FX) {
           if (clipFX != null) clipFX.loop(Clip.LOOP_CONTINUOUSLY);
       }
    }

    public void stop(byte i) {
       if (i == SoundType.MUSIC) {
           if (clipMusic != null) clipMusic.stop();
       } else if (i == SoundType.SOUND_FX) {
           if (clipFX != null) clipFX.stop();
       }
    }

    public void setVolume(float value, byte i) {
       if (i == SoundType.MUSIC) {
           if (value == -40){
               musicVolume = -80;
           } else {
               musicVolume = value;
           }
           if (gainControlMusic != null) gainControlMusic.setValue(musicVolume);
       } else if (i == SoundType.SOUND_FX) {
           if (value == -40){
               fxVolume = -80;
           } else {
               fxVolume = value;
           }
           if (gainControlFX != null) gainControlFX.setValue(fxVolume);
       }
    }

    public static Float getVolume(byte i) {
       if (i == SoundType.MUSIC) {
           return musicVolume;
       } else if (i == SoundType.SOUND_FX) {
           return fxVolume;
       } else {
           throw new NullPointerException();
       }
    }
}
