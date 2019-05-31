package entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Player_One extends Object {

    public Player_One(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        super(name, status, lastMove, lastIndex);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Player_One/Player_One_Left.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Player_One/Player_One_Up.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Player_One/Player_One_Right.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/"+ getTexturePack() +"/Player_One/Player_One_Down.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}
