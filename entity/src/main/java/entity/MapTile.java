package entity;

public class MapTile {
    private String name;
    private int x;
    private int y;

    public MapTile(String name, int x, int y){
        this.setName(name);
        this.setX(x);
        this.setY(y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
