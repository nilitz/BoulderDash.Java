package entity;

public class MapTile {
    private Object object;
    private int x;
    private int y;

    public MapTile(Object object, int x, int y){
        this.setObject(object);
        this.setX(x);
        this.setY(y);
    }

    public void printMapTile(){
        System.out.println(this.getObject().getName() + " | " + this.getX() + " | " + this.getY());
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Object getObject() {
        return this.object;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
