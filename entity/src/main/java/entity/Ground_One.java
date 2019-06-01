package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * The Class Ground_One
 *
 * @author De Grossi Hugo
 */
public class Ground_One extends Object {
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
    public Ground_One(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_One/Ground_One.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_One/Ground_One.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_One/Ground_One.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getResourcesPack() +"/Ground_One/Ground_One.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
