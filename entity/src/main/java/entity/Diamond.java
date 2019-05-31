package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Diamond extends Object{

    public Diamond(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Diamond/Diamond_1.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Diamond/Diamond_2.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Diamond/Diamond_3.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Diamond/Diamond_4.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
