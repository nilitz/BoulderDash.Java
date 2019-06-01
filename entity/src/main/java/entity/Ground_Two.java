package entity;


import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * The Class Ground_Two
 *
 * @author De Grossi Hugo
 */
public class Ground_Two extends Object {
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
    public Ground_Two(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }
    /**
     * Override of loadimage - load all the image of an object
     */
    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_Two/Ground_Two.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_Two/Ground_Two.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_Two/Ground_Two.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_Two/Ground_Two.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}