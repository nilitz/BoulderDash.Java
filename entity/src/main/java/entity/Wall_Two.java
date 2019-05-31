package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wall_Two extends Object {
    public Wall_Two(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Wall_Two/Wall_Two_1.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Wall_Two/Wall_Two_2.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Wall_Two/Wall_Two_3.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Wall_Two/Wall_Two_4.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
