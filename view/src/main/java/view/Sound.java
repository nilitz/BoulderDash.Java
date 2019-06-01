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

    /* The ArrayList musicFiles (contains path to music)**/
    private ArrayList<String> musicFiles;

    /**
     * The constructor Sound
     *
     * @param files
     * Take an undefined amount of files in parameter
     */
    public Sound(String...files){
        musicFiles = new ArrayList<String>();
        for(String file : files)
            musicFiles.add("C:\\Users\\hugod\\Documents\\JPU-BlankProject\\view\\src\\main\\resources\\sound\\" + file +".wav");
    }

    /**
     * Play the sound in the ArrayList
     *
     * @param fileName
     * The filename
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
            floatControl.setValue(-15);
            clip.start();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    /**
     * Run the sound
     */
    public void run() {
        playSound(musicFiles.get(0));
    }
}
