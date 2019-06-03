package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * The Class Diamond
 *
 * @author De Grossi Hugo
 */
public class Diamond extends Object{
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
    public Diamond(String name, boolean status, LastMove lastMove, int lastIndex) {
        super(name, status, lastMove, lastIndex);
    }
    /**
     * Override of loadimage - load all the image of an object
     */
    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Diamond/Diamond_1.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Diamond/Diamond_2.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Diamond/Diamond_3.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Diamond/Diamond_4.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
