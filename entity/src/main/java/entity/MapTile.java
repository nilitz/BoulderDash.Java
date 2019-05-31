package entity;
/**
 * The class MapTile
 * @author HugoDegrossi
 */
public class MapTile {
    private Object object;
    private int x;
    private int y;

    /**
     * Constructor
     * @param object
     * @param x
     * @param y
     */
    public MapTile(Object object, int x, int y){
        this.setObject(object);
        this.setX(x);
        this.setY(y);
    }

    /**
     * Print the MapTile in the Console
     */
    public void printMapTile(){
        System.out.println(this.getObject().getName() + " | " + this.getX() + " | " + this.getY());
    }

    /**
     * Setter of Object
     * @param object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Setter of X
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter of Y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter of object
     * @return
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * Getter of X
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Getter of Y
     * @return
     */
    public int getY() {
        return y;
    }
}
