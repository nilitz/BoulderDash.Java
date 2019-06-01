package entity;
/**
 * The Class MapTile
 *
 * @author De Grossi Hugo
 */
public class MapTile {
    private Object object;
    private int x;
    private int y;

    /**
     * MapTile Constructor
     * @param object
     * a Object type
     * @param x
     * position X
     * @param y
     * position Y
     */
    public MapTile(Object object, int x, int y){
        this.setObject(object);
        this.setX(x);
        this.setY(y);
    }

    /**
     * Object setter
     * @param object
     * set the Object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * X setter
     * @param x
     * set the position X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Y setter
     * @param y
     * set the position Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Object Getter
     * @return
     * return the Object
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * X getter
     * @return
     * return the position X
     */
    public int getX() {
        return x;
    }

    /**
     * Y getter
     * @return
     * return the position Y
     */
    public int getY() {
        return y;
    }
}
