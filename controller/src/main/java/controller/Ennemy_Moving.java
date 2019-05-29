package controller;
import controller.Falling;



public class Ennemy_Moving {

    public String getLast_Direction() {
        return Last_Direction;
    }

    public void setLast_Direction(String last_Direction) {
        Last_Direction = last_Direction;
    }

    private String Last_Direction ;
    
    public void Move_Enemy(int index){

  
            if (Entity_Check(index, Falling.DOWN).equals("Ground_One") || Entity_Check(index, Falling.DOWN).equals("Wall_One") || Entity_Check(index, Falling.DOWN).equals("Wall_Two") &&Entity_Check(index, Falling.LEFT).equals("Ground_Two") ){
                   Move_Monster(index, Falling.LEFT, mapTiles.get(index).getName());
                   setLast_Direction("Left");
            }
            else if (Entity_Check(index, Falling.LEFT).equals("Ground_One") || Entity_Check(index, Falling.LEFT).equals("Wall_One") || Entity_Check(index, Falling.LEFT).equals("Wall_Two") &&Entity_Check(index, Falling.UP).equals("Ground_Two") ){
                Move_Monster(index, Falling.UP, mapTiles.get(index).getName());
                setLast_Direction("Up");
            }
            else if (Entity_Check(index, Falling.UP).equals("Ground_One") || Entity_Check(index, Falling.UP).equals("Wall_One") || Entity_Check(index, Falling.UP).equals("Wall_Two") &&Entity_Check(index, Falling.RIGHT).equals("Ground_Two") ){
                Move_Monster(index, Falling.RIGHT, mapTiles.get(index).getName());
                setLast_Direction("Right");
            }
            else if (Entity_Check(index, Falling.RIGHT).equals("Ground_One") || Entity_Check(index, Falling.RIGHT).equals("Wall_One") || Entity_Check(index, Falling.RIGHT).equals("Wall_Two") &&Entity_Check(index, Falling.DOWN).equals("Ground_Two") ){
                Move_Monster(index, Falling.DOWN, mapTiles.get(index).getName());
                setLast_Direction("Down");
            }
            else{
                switch (Last_Direction){
                    case "Right":
                        Move_Monster(index, Falling.UP, mapTiles.get(index).getName() );
                        break;
                    case "Up":
                        Move_Monster(index, Falling.LEFT, mapTiles.get(index).getName() );
                        break;

                    case "Left":
                        Move_Monster(index, Falling.DOWN, mapTiles.get(index).getName() );
                        break;

                    case "Down":
                        Move_Monster(index, Falling.RIGHT, mapTiles.get(index).getName() );
                        break;




                }
            }


        }

    
    public void Move_Monster(int index, int direction, String Monster){
        mapTiles.get(index + direction).setName(Monster);
        mapTiles.get(index).setName("Ground_Two")


    }
    public String Entity_Check (int index, int Direction){
        return mapTiles.get(index + Direction).getName();
    }









}

