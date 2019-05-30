package view;
import sun.audio.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Sound {

    private ArrayList<String> musicFiles;
    private int currentSongIndex;

    public Sound(String...files){
        musicFiles = new ArrayList<String>();
        for(String file : files)
            musicFiles.add("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sound\\Map.wav");
    }

    private void playSound(String fileName) throws IOException {
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

    public void run() throws IOException {
        playSound(musicFiles.get(currentSongIndex));
    }
}
