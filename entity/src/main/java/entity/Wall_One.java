package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wall_One extends Object {
    public Wall_One(String name, boolean status, LastMove lastMove) throws IOException {
        super(name, status, lastMove);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Wall_One/Wall_One.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Wall_One/Wall_One.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Wall_One/Wall_One.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Wall_One/Wall_One.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
