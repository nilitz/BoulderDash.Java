package contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import entity.MapTile;

/**
 * The Interface IModel.
 *
 * @author De Grossi Hugo
 */
public interface IModel {

	/**
	 * Map getter
	 * @return
	 * return the ArrayList of Maptiles (= the Map)
	 */
	ArrayList<MapTile> getMap();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
	/**
	 *  ArrayList of Maptiles (launch DAO.getMapSQL(ID)) Setter
	 * @param ID
	 * ID of the Map | for DB
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	void setMap(int ID) throws SQLException, IOException;
	/**
	 * Map ID Getter
	 * @return
	 * return the ID of the Map - for DB import
	 */
	int getID();
	/**
	 * Map ID Setter
	 * @param ID
	 * ID of the Map - for DB import
	 */
	void setID(int ID);
	/**
	 * Getter SizeMap
	 * @return
	 * return the size of the map (number of case width x number of case height)
	 * @throws SQLException
	 * throws sql related exception
	 */
	int[] getSize() throws SQLException;
	/**
	 * ArrayMap Width (number of case) Getter
	 * @return
	 * return the width of the arrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	int getMapWidth() throws SQLException;
	/**
	 * ArrayMap Height (number of case) Getter
	 * @return
	 * return the height of the arrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
    int getMapHeight() throws SQLException;
	/**
	 * Generate the movement of the Player according to the Controller entries
	 * @param direction
	 * direction indicated by the Controller
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	void movement(String direction) throws SQLException, IOException;
	/**
	 * Enemy AI (MOVING) | Check Every Moving Case according to the last move
	 * @param index
	 * index of the actual position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	void enemyAutoMove(int index) throws SQLException, IOException;
	/**
	 * Rocks and Diamonds auto-fall and Call enemyautomove
	 * @param index
	 * index of the actual position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 * @throws InterruptedException
	 * throws thread related exception
	 * @throws IOException
	 * throws image related exception
	 */
	void autoMove(int index) throws SQLException, InterruptedException, IOException;
	/**
	 * Check if Player Status is True or False
	 * @return
	 * return true : playerIsDead | false : playerIsAlive
	 */
	boolean playerStatus();
	/**
	 * Win Getter
	 * @return
	 * return true if win
	 */
	boolean getWin();
	/**
	 * Win Setter (true = win)
	 * @param win
	 * boolean : true = WIN | false : NOT WIN YET
	 */
	void setWin(boolean win);
	/**
	 * Diamonds Counter Setter
	 * @param diamondCounter
	 * diamonds counter, set to 0 at every launch of a new map
	 */
	void setDiamondCounter(int diamondCounter);
	/**
	 * Diamonds Goal Getter
	 * @return
	 * return the number of diamonds to have
	 */
	int getDiamondToHave();
	/**
	 * Diamonds Counter Getter
	 * @return
	 * return the diamondCounter
	 */
	int getDiamondCounter();
	/**
	 * Diamonds Number Getter
	 * @param ID
	 * ID of the map | DB related
	 * @return
	 * return the diamond number to have in the map
	 * @throws SQLException
	 * throws sql related exception
	 */
	int getDiamondNumber(int ID) throws SQLException;
	/**
	 * Diamonds Goal Setter
	 * @param diamondToHave
	 * diamonds to have from the DB
	 */
	void setDiamondToHave(int diamondToHave);
	/**
	 * @param moveable
	 * boolean moveable : if true = player is Moveable | false = player is not Moveable
	 */
	void setMoveable(boolean moveable);

}