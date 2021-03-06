package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Rock extends Object {
    /**
     * Constructor of Object
     * @param name
     * name of the object
     * @param status
     * status of the object
     * @param lastMove
     * lastmove of the object
     * @param lastIndex
     * lastindex of the object
     * @throws IOException
     * throws image related exception
     */
    public Rock(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }
    /**
     * Override of loadimage - load all the image of an object
     */
    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Rock/Rock_1.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Rock/Rock_2.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Rock/Rock_3.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Rock/Rock_4.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }

    }
}
