package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * The Class Wall_Two
 *
 * @author De Grossi Hugo
 */
public class Wall_Two extends Object {
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
     */
    public Wall_Two(String name, boolean status, LastMove lastMove, int lastIndex) {
        super(name, status, lastMove, lastIndex);
    }
    /**
     * Override of loadimage - load all the image of an object
     */
    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Wall_Two/Wall_Two_1.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Wall_Two/Wall_Two_2.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Wall_Two/Wall_Two_3.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Wall_Two/Wall_Two_4.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
