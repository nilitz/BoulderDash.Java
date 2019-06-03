package entity;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * TEST | The Class MapTileTest
 *
 * @author De Grossi Hugo
 */
public class MapTileTest {

    /**
     * TEST | Testing Getter and Setter of an Object
     * @throws IOException
     * throws Image related exception
     */
    @Test
    public void setAndGetObject() throws IOException {
        Rock rockTest1 = new Rock("FIRST", true, LastMove.NOTHING, 0);
        Rock rockTest2 = new Rock("EXPECTED", true, LastMove.NOTHING, 0);
        MapTile mapTile = new MapTile(rockTest1, 0, 0);
        //TESTING BEFORE CHANGE
        assertEquals("FIRST", mapTile.getObject().getName());
        //CHANGING THE OBJECT
        mapTile.setObject(rockTest2);
        //TESTING AFTER CHANGE
        assertEquals("EXPECTED", mapTile.getObject().getName());
    }

    /**
     * TEST | Testing the Getter and Setter of X and Y positions
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void setXAndSetYAndGetXAndGetY() throws IOException {
        Rock rockTest1 = new Rock("FIRST", true, LastMove.NOTHING, 0);
        MapTile mapTile = new MapTile(rockTest1, 0, 0);
        //TESTING BEFORE THE CHANGE
        assertEquals(0, mapTile.getY());
        assertEquals(0, mapTile.getX());
        //CHANGING THE NAME
        mapTile.setY(100);
        mapTile.setX(200);
        //TESTING AFTER CHANGING
        assertEquals(100, mapTile.getY());
        assertEquals(200, mapTile.getX());
    }
}