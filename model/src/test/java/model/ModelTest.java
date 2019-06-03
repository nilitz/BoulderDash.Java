package model;

import contract.ControllerOrder;
import entity.*;
import entity.Object;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * TEST | The Class ModelTest
 *
 * @author De Grossi Hugo
 */
public class ModelTest {
    /** expected String n°1 */
    private String expectedString1;
    /** expected String n°2 */
    private String expectedString2;
    /** expected String n°3 */
    private String expectedString3;
    /** expected int n°1 */
    private int expectedInt1;
    /** expected int n°2 */
    private int expectedInt2;
    /** expected int n°3 */
    private int expectedInt3;
    /** index n°1 */
    private int index1;
    /** index n°2 */
    private int index2;
    /** index n°3 */
    private int index3;
    /** Instantiate new Model */
    Model model = new Model();

    /**
     * The constructor of ModelTest
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     */
    public ModelTest() throws IOException, SQLException {
        model.setID(4);
    }

    /**
     * TEST | swap method (between two objects)
     */
    @Test
    public void swap() {
        index1 = 20;
        index2 = 21;
        expectedString1 = model.getMap().get(index1).getObject().getName();
        expectedString2 = model.getMap().get(index2).getObject().getName();

        model.swap(index1, index2);

        assertEquals(expectedString1, model.getMap().get(index2).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(index1).getObject().getName());

    }

    /**
     * swap two Objects and convert one into Ground Two
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void swapToGroundTwo() throws IOException {

        index1 = 20;
        index2 = 21;
        expectedString1 = model.getMap().get(index1).getObject().getName();
        expectedString2 = model.getMap().get(index2).getObject().getName();
        expectedString3 = "Ground_Two";

        model.swapToGroundTwo(index1, index2);

        assertEquals(expectedString1, model.getMap().get(index2).getObject().getName());
        assertEquals(expectedString3, model.getMap().get(index1).getObject().getName());
    }

    /**
     * TEST | Change an object to Ground_Two
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void changeObjectToGroundTwo() throws IOException {
        expectedString1 = "Wall_One";
        index1 = 1;
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        model.changeObjectToGroundTwo(index1);
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());

        //----------------------------------------//

        expectedString1 = "Player_One";
        expectedString2 = "Ground_Two";
        index1 = 20;
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        model.changeObjectToGroundTwo(index1);
        assertEquals(expectedString2, model.getMap().get(index1).getObject().getName());
    }

    /**
     * TEST | Change an object into a diamond
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void changeObjectToDiamond() throws IOException {
        //Testing WALL_ONE CONDITION
        expectedString1 = "Wall_One";
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        expectedString1 = "Wall_One";
        index1 = 1;
        model.changeObjectToDiamond(index1);
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());

        //----------------------------------------//

        //TESTING IF IT PASSED WALL_ONE CONDITION
        expectedString1 = "Player_One";
        index1 = 20;
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        expectedString1 = "Diamond";
        model.changeObjectToDiamond(index1);
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
    }

    /**
     * TEST | the player status : True = isDead
     */
    @Test
    public void playerStatus() {
        assertFalse(model.playerStatus());

    }

    /**
     * TEST | Check if a rock is falling
     */
    @Test
    public void checkFalling() {
        for(int i = 0; i < model.getMap().size() ; i++){
            if (model.getMap().get(i).getObject().getName().equals("Rock")){
                assertFalse(model.checkFalling(i));
            }
        }

    }

    /**
     * TEST | Locate the player in the ArrayList
     */
    @Test
    public void locatePlayer() {
        model.locatePlayer();
        index1 = model.getIndexPlayer();
        expectedString1= "Player_One";
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());

    }

    /**
     * TEST | Check the name of the player next case
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void playerNextCase() throws SQLException {
        model.setDirection(model.getMapWidth());
        model.locatePlayer();
        assertEquals("Ground_Two",model.playerNextCase());
    }

    /**
     * TEST | Check a next case with an index and a direction
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void nextCase() throws SQLException {
        assertEquals("Ground_Two",model.nextCase(20, model.getMapWidth()));
    }

    /**
     * TEST | check a zone and return a code according to the object checked
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void aroundCode() throws SQLException {
        expectedString1 = "Ground_Two";
        expectedString2 = "Player_One";
        expectedString3 = "Wall_One";
        expectedInt1 = 0;
        expectedInt2 = 1;
        expectedInt3 = 2;
        index1 = 20;
        index2 = 39;

        //CASE 1
        assertEquals(expectedString1, model.getMap().get(index1 + model.getMapWidth()).getObject().getName());
        assertEquals(expectedInt1, model.aroundCode(index1, + model.getMapWidth()));

        //CASE 2
        assertEquals(expectedString2, model.getMap().get(index2 - model.getMapWidth()).getObject().getName());
        assertEquals(expectedInt2, model.aroundCode(index2, - model.getMapWidth()));

        //CASE 3
        assertEquals(expectedString3, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());
        assertEquals(expectedInt3, model.aroundCode(index1, - model.getMapWidth()));
    }

    /**
     * TEST | Change the lastMove of an entity
     */
    @Test
    public void changeLastMove() {
        index1 = 20;
        //CASE RIGHT
        model.changeLastMove(index1, LastMove.RIGHT);
        assertEquals(LastMove.RIGHT, model.getMap().get(index1).getObject().getLastMove());
        //CASE LEFT
        model.changeLastMove(index1, LastMove.LEFT);
        assertEquals(LastMove.LEFT, model.getMap().get(index1).getObject().getLastMove());
        //CASE UP
        model.changeLastMove(index1, LastMove.UP);
        assertEquals(LastMove.UP, model.getMap().get(index1).getObject().getLastMove());
        //CASE DOWN
        model.changeLastMove(index1, LastMove.DOWN);
        assertEquals(LastMove.DOWN, model.getMap().get(index1).getObject().getLastMove());
        //CASE NOTHING
        model.changeLastMove(index1, LastMove.NOTHING);
        assertEquals(LastMove.NOTHING, model.getMap().get(index1).getObject().getLastMove());

    }

    /**
     * TEST | Movement method for the player
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void movement() throws IOException, SQLException {
        //SHOULD MOVE TO DOWN
        model.locatePlayer();
        index1 = model.getIndexPlayer();
        model.movement("DOWN");
        expectedString1 = "Player_One";
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1 + model.getMapWidth(), model.getIndexPlayer());
        index1 = index1 + model.getMapWidth();

        //SHOULDN'T MOVE, THERE'S A WALL
        model.movement("LEFT");
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1, model.getIndexPlayer());

        //SHOULD MOVE TO RIGHT
        model.movement("RIGHT");
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1 + 1, model.getIndexPlayer());
        index1 = index1 + 1;

        //SHOULD MOVE TO DOWN
        model.movement("DOWN");
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1 + model.getMapWidth(), model.getIndexPlayer());
        index1 = index1 + model.getMapWidth();

        //SHOULD MOVE TO UP
        model.movement("UP");
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1 - model.getMapWidth(), model.getIndexPlayer());
        index1 = index1 - model.getMapWidth();

        //SHOULD MOVE TO LEFT
        model.movement("LEFT");
        assertEquals(expectedString1, model.getMap().get(model.getIndexPlayer()).getObject().getName());
        assertEquals(index1 - 1, model.getIndexPlayer());

    }

    /**
     * TEST | Launch method according to the nextPlayerCase
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void switchArray() throws IOException, SQLException {
        model.locatePlayer();
        index1 = model.getIndexPlayer();
        index2 = index1 + model.getMapWidth();
        expectedString1 = "Player_One";
        expectedString2 = "Rock";
        expectedString3 = "Diamond";

        //CASE MOVE ON GROUND
        model.setDirection(model.getMapWidth());
        model.setControllerOrder(ControllerOrder.DOWN);
        model.switchArray();
        assertEquals(expectedString1, model.getMap().get(index2).getObject().getName());

        //CASE MOVE ON WALL
        model.setDirection(model.getMapWidth());
        model.setControllerOrder(ControllerOrder.DOWN);
        model.switchArray();
        assertEquals(expectedString1, model.getMap().get(index2).getObject().getName());

        //CASE MOVE ON ROCK (MOVING)
        index3 = 62;
        model.swap(index2, index3);
        model.setDirection(1);
        model.setControllerOrder(ControllerOrder.RIGHT);
        model.switchArray();
        assertEquals(expectedString1, model.getMap().get(index3 + 1).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(index3 + 2).getObject().getName());

        //CASE MOVE ON ROCK (NOT MOVING)
        index1 = index3 + 2 + model.getMapWidth();
        model.locatePlayer();
        model.swap(model.getIndexPlayer(), index1);
        model.locatePlayer();
        model.setDirection(- 1);
        model.setControllerOrder(ControllerOrder.LEFT);
        model.switchArray();
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(index1 - 1).getObject().getName());

        //CASE MOVE ON DIAMOND
        index2 = index1 - 2 + model.getMapWidth();
        model.locatePlayer();
        model.swap(model.getIndexPlayer(), index2);
        model.locatePlayer();
        model.setDirection(- 1);
        model.setControllerOrder(ControllerOrder.LEFT);
        assertEquals(expectedString3, model.getMap().get(index2 - 1).getObject().getName());
        model.switchArray();
        assertEquals(expectedString1, model.getMap().get(index2 - 1).getObject().getName());
  }

    /**
     * TEST | Move the player
     * @throws SQLException
     * throws sql related exception
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void movePlayer() throws SQLException, IOException {
        expectedString1 = "Player_One";
        expectedString2 = "Ground_Two";
        expectedString3 = "Diamond";

        //CASE MOVE ON GROUND
        model.locatePlayer();
        index2 = model.getIndexPlayer();
        model.setDirection(model.getMapWidth());
        //CHECK IF THE NEXT CASE IS A GROUND
        assertEquals(expectedString2, model.getMap().get(index2 + model.getMapWidth()).getObject().getName());
        model.movePlayer();
        assertEquals(expectedString1, model.getMap().get(index2 + model.getMapWidth()).getObject().getName());

        //CASE MOVE ON DIAMOND
        index2 = model.getMapWidth() * 5 + 3;
        model.locatePlayer();
        model.swap(model.getIndexPlayer(), index2);
        model.locatePlayer();
        model.setDirection(1);
        //CHECK IF THE NEXT CASE IS A DIAMOND
        assertEquals(expectedString3, model.getMap().get(index2 + 1).getObject().getName());
        model.movePlayer();
        assertEquals(expectedString1, model.getMap().get(index2 + 1).getObject().getName());
    }

    /**
     * TEST | Move the Enemy
     * @throws SQLException
     * throws sql related exception
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void moveEnemy() throws SQLException, IOException {
        index1 = 159;
        expectedString1 = "Enemy_One";
        expectedString2 = "Wall_Two";
        expectedString3 = "Ground_Two";

        //MOVING ON WALL
        //CHECK IF THE ENEMY IS ON THE INITIAL POSITION
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        //CHECK IF THERE'S A WALL
        assertEquals(expectedString2, model.getMap().get(index1 + model.getMapWidth()).getObject().getName());
        model.moveEnemy(index1, model.getMapWidth());
        //THE ENEMY SHOULD NOT MOVE
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());

        //MOVING ON GROUND
        //CHECK IF THE ENEMY IS ON THE INITIAL POSITION
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        //CHECK IF THERE'S GROUND
        assertEquals(expectedString3, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());
        model.moveEnemy(index1, - model.getMapWidth());
        //THE ENEMY SHOULD HAVE MOVED
        assertEquals(expectedString1, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());

        //MOVING ON PLAYER
        model.swap(index1 - model.getMapWidth(), 39);
        index1 = 39;
        expectedString3 = "Player_One";
        //CHECK IF THE ENEMY IS ON THE INITIAL POSITION
        assertEquals(expectedString1, model.getMap().get(index1).getObject().getName());
        //CHECK IF THERE'S A PLAYER
        assertEquals(expectedString3, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());
        model.moveEnemy(index1, - model.getMapWidth());
        //THE ENEMY SHOULD HAVE MOVED
        assertEquals(expectedString1, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());
    }


    /**
     * TEST | Move the Rocks and Diamonds + launch the method to move the enemy
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     * @throws InterruptedException
     * throws thread related exception
     */
    @Test
    public void autoMove() throws IOException, SQLException, InterruptedException {
        expectedString1 = "Enemy_One";
        //CREATION OF A 3X3 SQUARE FOR THE ENEMY
        model.changeObjectToGroundTwo(30 );
        model.changeObjectToGroundTwo(31);
        model.changeObjectToGroundTwo(32);
        model.changeObjectToGroundTwo(49);
        model.changeObjectToGroundTwo(50);
        model.changeObjectToGroundTwo(51);
        model.changeObjectToGroundTwo(68);
        model.changeObjectToGroundTwo(69);
        model.changeObjectToGroundTwo(70);
        //SO THE ENEMY CAN TURN (THERE WAS NO BLOCK HERE BEFORE)
        model.changeObjectToDiamond(33);

        model.swap(159, 51);
        //INITIATE THE MOVE
        model.getMap().get(51).getObject().setLastMove(LastMove.DOWN);
        model.autoMove(51);
        assertEquals(expectedString1, model.getMap().get(70).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(70);
        model.autoMove(70);
        assertEquals(expectedString1, model.getMap().get(69).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(69);
        model.autoMove(69);
        assertEquals(expectedString1, model.getMap().get(68).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(68);
        model.autoMove(68);
        assertEquals(expectedString1, model.getMap().get(49).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(49);
        model.autoMove(49);
        assertEquals(expectedString1, model.getMap().get(30).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(30);
        model.autoMove(30);
        assertEquals(expectedString1, model.getMap().get(31).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(31);
        model.autoMove(31);
        assertEquals(expectedString1, model.getMap().get(32).getObject().getName());
        //INITIATE THE MOVE
        model.autoMove(32);
        model.autoMove(32);
        assertEquals(expectedString1, model.getMap().get(51).getObject().getName());
        //RETURN TO THE SAME POSITION

    }

    /**
     * TEST | Generate a diamond Square when a rock fall on a living entity
     * @throws InterruptedException
     * throws thread related exception
     * @throws SQLException
     * throws sql related exception
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void diamondsTNT() throws InterruptedException, SQLException, IOException {

        expectedString1 = "Diamond";
        expectedString2 = "Wall_One";

        //CASE THE EXPLOSION HAPPENNED IN A CORNER
        index1 = 20;
        model.diamondsTNT(index1);
        assertEquals(expectedString2, model.getMap().get(0).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(1).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(2).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(19).getObject().getName());
        assertEquals(expectedString1, model.getMap().get(20).getObject().getName());
        assertEquals(expectedString1, model.getMap().get(21).getObject().getName());
        assertEquals(expectedString2, model.getMap().get(38).getObject().getName());
        assertEquals(expectedString1, model.getMap().get(39).getObject().getName());
        assertEquals(expectedString1, model.getMap().get(40).getObject().getName());

        //CASE THERE'S NO WALL_ONE : SHOULD HAVE 3X3 DIAMONDS SQUARE
        index1 = 200;
        model.diamondsTNT(index1);
        for (int i = index1 - 1;i <= index1 + 1; i ++){
            assertEquals(expectedString1, model.getMap().get(i).getObject().getName());
        }
        for (int i = index1 + model.getMapWidth() - 1;i <= index1 + model.getMapWidth() + 1; i ++){
            assertEquals(expectedString1, model.getMap().get(i).getObject().getName());
        }
        for (int i = index1 + 2 * model.getMapWidth() - 1;i <= index1 + 2 * model.getMapWidth() + 1; i ++){
            assertEquals(expectedString1, model.getMap().get(i).getObject().getName());
        }
    }

    /**
     * TEST | Move the rock if the player want to push him
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void moveRock() throws IOException, SQLException {
        expectedString1 = "Rock";
        expectedString2 = "Player_One";
        //CAN MOVE / LEFT TO RIGHT
        index1 = 62;
        model.swap(20, 62);
        model.locatePlayer();
        model.setDirection(1);
        model.moveRock();
        assertEquals(expectedString1, model.getMap().get(index1 + 2).getObject().getName());
        assertEquals(expectedString2,  model.getMap().get(index1 + 1).getObject().getName());
        //CAN'T MOVE / RIGHT TO LEFT
        index1 = 83;
        model.locatePlayer();
        model.swap(model.getIndexPlayer(), 83);
        model.locatePlayer();
        model.setDirection(- 1);
        model.moveRock();
        assertEquals(expectedString1, model.getMap().get(index1 - 1).getObject().getName());
        assertEquals(expectedString2,  model.getMap().get(index1).getObject().getName());

        //CAN'T MOVE / DOWN TO UP
        index1 = 139;
        model.locatePlayer();
        model.swap(model.getIndexPlayer(), 139);
        model.locatePlayer();
        model.setDirection( - model.getMapWidth());
        model.moveRock();
        assertEquals(expectedString1, model.getMap().get(index1 - model.getMapWidth()).getObject().getName());
        assertEquals(expectedString2,  model.getMap().get(index1).getObject().getName());
    }

    /**
     * TEST | choose where the rock / diamond will fall
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     * @throws InterruptedException
     * throws thread related exception
     */
    @Test
    public void whereToMove() throws IOException, SQLException, InterruptedException {
        //PREPARING MAP TO HAVE A GROUND CROSSABLE SQUARE W/ ONE DIAMOND SO THE ROCK FALL ON IT
        model.changeObjectToGroundTwo(30 );
        model.changeObjectToGroundTwo(31);
        model.changeObjectToGroundTwo(32);
        model.changeObjectToGroundTwo(49);
        model.changeObjectToGroundTwo(50);
        model.changeObjectToGroundTwo(51);
        model.changeObjectToGroundTwo(68);
        model.changeObjectToDiamond(69);
        model.changeObjectToGroundTwo(70);
        model.swap(120, 50);
        //SETTING ROCK IS FALLING TRUE
        model.getMap().get(50).getObject().setStatus(true);

        model.whereToMove(50);
        //THE ROCK SHOULD BE BOTTOM LEFT
        assertEquals("Rock", model.getMap().get(68).getObject().getName());

        model.swap(54, 50);
        model.whereToMove(50);
        //THE ROCK SHOULD BE BOTTOM RIGHT
        assertEquals("Rock", model.getMap().get(70).getObject().getName());

        model.swap(73, 50);
        model.whereToMove(50);
        //THE ROCK SHOULD NOT MOVE
        assertEquals("Rock", model.getMap().get(50).getObject().getName());

    }

    /**
     * TEST | Reset the counter, and set the win to true
     */
    @Test
    public void gameWin() {
        model.setWin(false);
        model.setDiamondCounter(5);
        model.gameWin();
        assertTrue(model.getWin());
        assertEquals(0, model.getDiamondCounter());
    }

    /**
     * TEST | Collect Diamond (By the Player)
     */
    @Test
    public void collectDiamond() {
        model.setDiamondToHave(6);
        //TESTING FOR COLLECTING ONE DIAMOND
        model.setDiamondCounter(4);
        model.collectDiamond();
        assertEquals(5, model.getDiamondCounter());
        //TESTING FOR COLLECTING ONE DIAMOND AND WINNING
        model.collectDiamond();
        assertTrue(model.getWin());
        assertEquals(0, model.getDiamondCounter());
    }

    /**
     * TEST | MapWidth Getter
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void getMapWidth() throws SQLException {
        assertEquals(19,model.getMapWidth());
    }

    /**
     * TEST | MapHeight Getter
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void getMapHeight() throws SQLException {
        assertEquals(19,model.getMapHeight());
    }

    /**
     * TEST | Getter and Setter of the Map ID
     */
    @Test
    public void setIDAndGetID() {
        model.setID(3);
        assertEquals(3, model.getID());
    }

    /**
     * TEST | Getter and Setter of the Controller Order
     */
    @Test
    public void setAndGetControllerOrder() {
        model.setControllerOrder(ControllerOrder.LEFT);
        assertEquals(ControllerOrder.LEFT, model.getControllerOrder());
    }

    /**
     * TEST | Getter and Setter of the win
     */
    @Test
    public void setAndGetWin() {
        model.setWin(true);
        assertTrue(model.getWin());
    }


    /**
     * Getter and Setter of the number of diamonds to have
     */
    @Test
    public void setAndGetDiamondToHave() {
        model.setDiamondToHave(5);
        assertEquals(5, model.getDiamondToHave());
    }

    /**
     * Getter and Setter of the diamond counter
     */
    @Test
    public void setAndGetDiamondCounter() {
        model.setDiamondCounter(10);
        assertEquals(10, model.getDiamondCounter());
    }
}