package view;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The Class Sound
 *
 * @author De Grossi Hugo
 */
public class Sound {

    private ArrayList<String> musicFiles;
    private int currentSongIndex;

    /**
     * Sound constructor
     * @param files
     * take an unlimited number of files in parameter
     */
    public Sound(String...files){
        musicFiles = new ArrayList<String>();
        for(String file : files)
            musicFiles.add("K:\\[BOULDER DASH]\\JPU-BlankProject\\view\\src\\main\\resources\\sound\\"+ file +".wav");
            //musicFiles.add("./resources/sound/"+ file +".wav"); //DOESN'T WORK
    }

    /**
     * All the things needed to play the sound
     * @param fileName
     * the music filename
     */
    private void playSound(String fileName) {
        try {
            File soundFile = new File(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(-10);
            clip.start();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * run the music
     */
    public void run(){
        playSound(musicFiles.get(currentSongIndex));
    }

    /**
     * Index Setter (for the current song)
     * @param currentSongIndex
     * Take the index in parameters
     */
    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }
}
