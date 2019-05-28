package controller;

public class Falling {
    public static int LEFT=-1;
    public static int RIGHT=1;
    public static int DOWN = 10;
    public static int DOWN_LEFT=9;
    public static int DOWN_RIGHT=11;
    private boolean Is_Falling=false;
    private int Continue = 0;

    public int getContinue() {
        return Continue;
    }

    public void setContinue(int aContinue) {
        Continue = aContinue;
    }
    public boolean isIs_Falling() {
        return Is_Falling;
    }

    public void setIs_Falling(boolean is_Falling) {
        Is_Falling = is_Falling;
    }


    public void Entity_Falling(int index){
        setContinue(0);
        while (getContinue() == 0) {
            if (mapTiles.get(index).getName().equals("Rock") || mapTiles.get(index).getName().equals("Diamond")) {
                if (Void_Check(index,DOWN)) {
                    setIs_Falling(true);
                    if (mapTiles.get(index).getName().equals("Rock")){
                        Move_Stone(index,DOWN);}
                    if (mapTiles.get(index).getName().equals("Diamond")){
                        Move_Diamond(index, DOWN);
                    }
                }
                else if (Entity_Check(index,DOWN).equals("Rock") || mapTiles.get(index).getName().equals("Diamond")) {
                    if (Void_Check(index,LEFT)) {
                        if (Void_Check(index,DOWN_LEFT)) {
                            setIs_Falling(true);
                            if (mapTiles.get(index).getName().equals("Rock")){
                            Move_Stone(index,DOWN_LEFT);}
                            if (mapTiles.get(index).getName().equals("Diamond")){
                                Move_Diamond(index, DOWN_LEFT);
                            }
                        }
                    }
                    else if (Void_Check(index,RIGHT)) {
                        if (Void_Check(index,DOWN_RIGHT)) {
                            setIs_Falling(true);
                            if (mapTiles.get(index).getName().equals("Rock")){
                                Move_Stone(index,DOWN_RIGHT);}
                            if (mapTiles.get(index).getName().equals("Diamond")){
                                Move_Diamond(index, DOWN_RIGHT);
                            }
                        }
                    }

                }
                else {
                    setContinue(1);

                }
            }

        }
        setIs_Falling(false);
    }

    public void Move_Stone(int index, int direction){
        mapTiles.get(index + direction).setName("Rock");
        mapTiles.get(index).setName("Ground_Two")
    }
    public void Move_Diamond(int index, int direction){
        mapTiles.get(index + direction).setName("Diamond");
        mapTiles.get(index).setName("Ground_Two")
    }

    public String Entity_Check(int index, int direction){
        String Entity = mapTiles.get(index + direction).getName();

        return Entity;
    }


    public boolean Void_Check (int index, int direction){
        if (mapTiles.get(index + direction).getName().equals("Ground_Two")){
            return true;
        }
        else{
            return false;
        }
    }


}
