package model;

import contract.ControllerOrder;
import contract.IModel;
import entity.MapTile;

import java.util.ArrayList;

public class Motion implements IMotion {

    private ControllerOrder controllerOrder;

    private IModel model;

    private ArrayList<MapTile> mapTiles = new ArrayList<MapTile>();

    public Motion() {}

    /*
    ArrayList <String> Map = new ArrayList<>();

    public void MoveUpDown(int i, int d){
        if (Map.get(i).getName()="Dirt" || Map.get(i).getName()="Dirt2"){
            Map.get(i+d).setName()="Player";
            Map.get(i).setName()="Dirt 2";
        }
    }


    public void MoveLeftRight(int i, int d){
        if (Map.get(i+d).getName() == "Rock"){
            if (Map.get(i+(2*d)).getName()=="Dirt2"){
                Map.get(i+d).setName()="Player";
                Map.get(i+(2*d)).setName()="Rock";
                Map.get(i).setName()="Dirt2";

            }

        }
        else if (Map.get(i).getName()="Dirt" || Map.get(i).getName()="Dirt2"){
            Map.get(i+d).setName()="Player";
            Map.get(i).setName()="Dirt 2";
        }

    }

    public void orderPerform(final ControllerOrder controllerOrder) {
        for (int i = 0; i <= Map.size(); i++){
            if (Map.get(i).getName()=="Player"){
                Direction direction;
                switch (controllerOrder) {
                    case Up:
                        direction = Direction.Up;
                        break;
                    case Down:
                        direction = Direction.Down:
                        break;
                    case Left:
                        direction = Direction.Left;
                        break;
                    case Right:
                        direction = Direction.Right;
                        break;
                    case Nothing:
                        direction = Direction.Nothing;
                        break;

                    }
                }
            }
        }
    */
}



