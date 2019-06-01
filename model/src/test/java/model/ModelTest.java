package model;

import entity.*;
import entity.Object;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelTest {

    private ArrayList<String> mapExpected =  new ArrayList<String>();
    private ArrayList<String> map =  new ArrayList<String>();


    private String player_one = "Player_One";
    private boolean player_one_status = true;
    private String player_one_lastmove = "Left";
    private String ground_two = "Ground_Two";
    private String rock = "Rock";
    private boolean rock_status = true;
    private String ground_one = "Ground_One";
    private String diamond = "Diamond";
    private String wall_one = "Wall_One";
    private String enemy_one = "Enemy_One";


    private String temporary;
    private int depart;
    private int arrive;



    @Test
    public void swapTest() {

        depart = 0;
        arrive = depart + 1;

        map.add(0, player_one);
        map.add(1, ground_two);
        mapExpected.add(0, ground_two);
        mapExpected.add(1, player_one);

        //--------------- Swap method ---------------
        temporary = map.get(arrive);
        map.set(arrive, map.get(depart));
        map.set(depart, temporary);
        //--------------- Swap method ---------------

        assertEquals(mapExpected,map);

    }

    @Test
    public void swapToGroundTwo() {
        depart = 0;
        arrive = depart + 1;

        map.add(0, player_one);
        map.add(1, ground_one);
        mapExpected.add(0, ground_two);
        mapExpected.add(1, player_one);

        //--------------- Swap method ---------------
        map.set(arrive, map.get(depart));
        map.set(depart, ground_two);
        //--------------- Swap method ---------------

        assertEquals(mapExpected,map);
    }

    @Test
    public void changeObjectToGroundTwo() {
        map.add(0, ground_one);
        map.add(1, wall_one);
        mapExpected.add(0, ground_two);
        mapExpected.add(1, wall_one);

        //--------------- Swap method ---------------
        for (int i = 0; i < map.size(); i++){ if (!map.get(i).equals("Wall_One")) { map.set(i, ground_two); } }
        //--------------- Swap method ---------------

        assertEquals(mapExpected,map);
    }

    @Test
    public void changeObjectToDiamond() {
        map.add(0, ground_one);
        map.add(1, wall_one);
        mapExpected.add(0, diamond);
        mapExpected.add(1, wall_one);

        //--------------- Swap method ---------------
        for (int i = 0; i < map.size(); i++){ if (!map.get(i).equals("Wall_One")) { map.set(i, diamond); } }
        //--------------- Swap method ---------------

        assertEquals(mapExpected,map);
    }

    @Test
    public void playerStatus() {

        map.add(0, ground_one);
        map.add(1, player_one);

        //--------------- Swap method ---------------
        for (int i = 0; i < map.size(); i++){
            if (map.get(i).equals("Player_One") && !player_one_status) { assertTrue(!player_one_status); }
            else { assertTrue(player_one_status); }
        }
        //--------------- Swap method ---------------
    }

    @Test
    public void checkFalling() {
        map.add(0, rock);
        assertTrue(rock_status);

    }

    @Test
    public void locatePlayer() {

        int index = -1;
        int expected = 1;
        map.add(0, ground_one);
        map.add(1, player_one);

        //--------------- Swap method ---------------
        for (int i = 0; i < map.size(); i++){
            if (map.get(i).equals("Player_One")){
                index = i;
                assertEquals(expected, index);
            }
        }
        //--------------- Swap method ---------------
    }

    @Test
    public void playerNextCase() {
        depart = 1;
        int direction = - 1;
        String expected = "Ground_One";
        map.add(0, ground_one);
        map.add(1, player_one);
        assertEquals(map.get(depart + direction), expected);
    }

    @Test
    public void nextCase() {
        depart = 1;
        int direction = 1;
        String expected = "Ground_One";
        map.add(0, ground_two);
        map.add(1, player_one);
        map.add(2, ground_one);
        assertEquals(map.get(depart + direction), expected);
    }

    @Test
    public void aroundCode() {
        depart = 1;
        int direction = -1;
        int expected = direction + 1;
        int code;
        map.add(0, ground_two);
        map.add(1, player_one);
        map.add(2, rock);

        if (map.get(depart + direction).equals("Ground_Two")){
            code = 0;
            assertEquals(expected, code);
        } else if(map.get(depart + direction).matches("Player_One")){
            code = 1;
            assertEquals(expected, code);
        }else{
            code = 2;
            assertEquals(expected, code);
        }

    }

    @Test
    public void changeLastMove() {

    }

    @Test
    public void movement() {
    }

    @Test
    public void switchArray() {
    }

    @Test
    public void movePlayer() {
    }

    @Test
    public void moveEnemy() {
    }

    @Test
    public void moveEnemyRight() {
    }

    @Test
    public void moveEnemyDown() {
    }

    @Test
    public void moveEnemyLeft() {
    }

    @Test
    public void moveEnemyUp() {
    }

    @Test
    public void enemyAutoMove() {
    }

    @Test
    public void autoMove() {
    }

    @Test
    public void diamondsTNT() {
    }

    @Test
    public void moveRock() {
    }

    @Test
    public void whereToMove() {
    }

    @Test
    public void loadPlayerControl() {
    }

    @Test
    public void gameWin() {
    }

    @Test
    public void collectDiamond() {
    }

    @Test
    public void getMapWidth() {
    }

    @Test
    public void getMapHeight() {
    }

    @Test
    public void setID() {
    }

    @Test
    public void getID() {
    }

    @Test
    public void getSize() {
    }

    @Test
    public void getMap() {
    }

    @Test
    public void setMap() {
    }

    @Test
    public void getObservable() {
    }

    @Test
    public void setControllerOrder() {
    }

    @Test
    public void setWin() {
    }

    @Test
    public void getWin() {
    }

    @Test
    public void setDiamondToHave() {
    }

    @Test
    public void setDiamondCounter() {
    }

    @Test
    public void getDiamondCounter() {
    }

    @Test
    public void getDiamondToHave() {
    }

    @Test
    public void getDiamondNumber() {
    }
}