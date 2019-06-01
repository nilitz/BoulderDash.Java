package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * The Class Enemy_One
 *
 * @author De Grossi Hugo
 */
public class Enemy_One extends Object {
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
    public Enemy_One(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }
    /**
     * Override of loadimage - load all the image of an object
     */
    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Enemy_One/Enemy_One_Left.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Enemy_One/Enemy_One_Up.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Enemy_One/Enemy_One_Right.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Enemy_One/Enemy_One_Down.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
