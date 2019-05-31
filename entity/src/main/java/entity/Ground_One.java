package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ground_One extends Object {
    public Ground_One(String name, boolean status, LastMove lastMove) throws IOException {
        super(name, status, lastMove);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_One/Ground_One.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_One/Ground_One.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_One/Ground_One.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_One/Ground_One.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
