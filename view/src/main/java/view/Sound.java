package view;
import sun.audio.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class Sound
 */
public class Sound {
    /**
     * ArrayList of Music file
     */
    private ArrayList<String> musicFiles;
    private int currentSongIndex;

    /**
     * Adding all the music to the Array List
     * @param files
     */
    public Sound(String...files){
        musicFiles = new ArrayList<String>();
        for(String file : files)
            musicFiles.add("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sound\\" + file +".wav");
    }

    /**
     * Methods which set the song.
     * @param fileName
     * @throws IOException
     */
    private void playSound(String fileName) throws IOException {
        try {
            File soundFile = new File(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(-15);
            clip.start();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * Play the Music
     * @throws IOException
     */
    public void run() throws IOException {
        playSound(musicFiles.get(currentSongIndex));
    }
}
