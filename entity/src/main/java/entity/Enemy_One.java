package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy_One extends Object {
    public Enemy_One(String name, boolean status, LastMove lastMove) throws IOException {
        super(name, status, lastMove);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Enemy_One/Enemy_One_Left.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Enemy_One/Enemy_One_Up.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Enemy_One/Enemy_One_Right.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Enemy_One/Enemy_One_Down.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
