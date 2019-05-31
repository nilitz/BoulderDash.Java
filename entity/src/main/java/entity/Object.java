package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public abstract class Object {
    private String name;
    private boolean status;
    private LastMove lastMove;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private int lastIndex;
    private String texturePack = "NES2";



    public Object(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        this.setName(name);
        this.setStatus(status);
        this.setLastMove(lastMove);
        loadImage();
        this.setLastIndex(lastIndex);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public LastMove getLastMove() {
        return lastMove;
    }

    public void setLastMove(LastMove lastMove) {
        this.lastMove = lastMove;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public Image getImage1() {
        return image1;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage3(Image image3) {
        this.image3 = image3;
    }

    public Image getImage3() {
        return image3;
    }

    public void setImage4(Image image4) {
        this.image4 = image4;
    }

    public Image getImage4() {
        return image4;
    }

    public void loadImage() throws IOException { }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public String getTexturePack() {
        return texturePack;
    }
}
