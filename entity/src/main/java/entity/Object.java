package entity;

import java.awt.*;
import java.io.IOException;

/**
 * The abstract class Object
 *
 * @author De Grossi Hugo
 */
public abstract class Object {
    /** the name */
    private String name;
    /** the status */
    private boolean status;
    /** the lastMove */
    private LastMove lastMove;
    /** the image1 */
    private Image image1;
    /** the image2 */
    private Image image2;
    /** the image3 */
    private Image image3;
    /** the image4 */
    private Image image4;
    /** the lastIndex */
    private int lastIndex;
    /** the resourcesPack */
    private String resourcesPack = "NES2";


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
    public Object(String name, boolean status, LastMove lastMove, int lastIndex) throws IOException {
        this.setName(name);
        this.setStatus(status);
        this.setLastMove(lastMove);
        loadImage();
        this.setLastIndex(lastIndex);
    }


    /**
     * name getter
     * @return
     * return the name of an object
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name
     * set the name of an object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * status setter
     * @param status
     * set the status of an object
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * status getter
     * @return
     * return the status of an object
     */
    public boolean getStatus(){
        return status;
    }

    /**
     * lastmove getter
     * @return
     * return the last move of an object
     */
    public LastMove getLastMove() {
        return lastMove;
    }

    /**
     * lastmove setter
     * @param lastMove
     * set the last move of an object
     */
    public void setLastMove(LastMove lastMove) {
        this.lastMove = lastMove;
    }

    /**
     * image 1 setter
     * @param image1
     * set the image 1
     */
    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    /**
     * image 1 getter
     * @return
     * return the image 1
     */
    public Image getImage1() {
        return image1;
    }

    /**
     * image 2 setter
     * @param image2
     * set the image2
     */
    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    /**
     * image 2 getter
     * @return
     * return the image 2
     */
    public Image getImage2() {
        return image2;
    }

    /**
     * image 3 setter
     * @param image3
     * set the image 3
     */
    public void setImage3(Image image3) {
        this.image3 = image3;
    }

    /**
     * image 3 getter
     * @return
     * return the image 3
     */
    public Image getImage3() {
        return image3;
    }

    /**
     * image 4 setter
     * @param image4
     * set the image 4
     */
    public void setImage4(Image image4) {
        this.image4 = image4;
    }

    /**
     * image 4 getter
     * @return
     * return the image 4
     */
    public Image getImage4() {
        return image4;
    }

    /**
     * loadimage - load all the image of an object
     */
    public void loadImage() { }

    /**
     * lastindex setter
     * @param lastIndex
     * set the lastindex
     */
    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    /**
     * lastindex getter
     * @return
     * return the last index
     */
    public int getLastIndex() {
        return lastIndex;
    }

    /**
     * resourcespack getter
     * @return
     * return the resources pack
     */
    public String getResourcesPack() {
        return resourcesPack;
    }
}
