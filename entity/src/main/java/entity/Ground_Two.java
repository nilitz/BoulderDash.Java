package entity;


import javax.imageio.ImageIO;
import java.io.IOException;

public class Ground_Two extends Object {

    private ImageIO image1;
    private ImageIO image2;
    private ImageIO image3;
    private ImageIO image4;

    public Ground_Two(String name, boolean status, LastMove lastMove) throws IOException {
        super(name, status, lastMove);
    }

    @Override
    public void loadImage(){
        try{
            setImage1(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_Two/Ground_Two.png")));
            setImage2(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_Two/Ground_Two.png")));
            setImage3(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_Two/Ground_Two.png")));
            setImage4(ImageIO.read(getClass().getClassLoader().getResource("./sprites/NES/Ground_Two/Ground_Two.png")));
        }catch (final IOException e){
            e.printStackTrace();
        }
    }
}