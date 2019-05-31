package contract;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import entity.MapTile;

/**
 * The Interface IModel.
 *
 * @author Hugo Degrossi
 *
 */
public interface IModel {
	/**
	 * ArrayList which contains the mapTiles
	 *
	 */
	ArrayList<MapTile> getMap();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();

	/** Set the map
	 *
	 * @param ID
	 * @throws SQLException
	 */
	void setMap(int ID) throws SQLException;

	/**Getter  of  ID
	 *
	 *
	 */

	int getID();

	/**
	 * Setter of ID
	 * @param ID
	 */

	void setID(int ID);

	/**
	 * Get size from the map
	 * @return
	 * @throws SQLException
	 */
	int[] getSize() throws SQLException;

	/**
	 * Get the size in width of the map
	 * @return
	 * @throws SQLException
	 */
	int getMapWidth() throws SQLException;

	/**
	 * get the size in Height of the map
	 *
	 * @throws SQLException
	 */
    int getMapHeight() throws SQLException;

	/**
	 * Get the controller Order and load it
	 * @param controllerOrder
	 */
	void loadPlayerControl(ControllerOrder controllerOrder);

	/**
	 * Take the direction and make the movement of the player
	 * @param direction
	 * @throws SQLException
	 */
	void movement(String direction) throws SQLException;

	/**
	 * Get the index of an ennemy and moves it
	 * @param index
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	void enemyAutoMove(int index) throws SQLException, InterruptedException;

	/**
	 * Get the index of a rock or a diamon and automoves it
	 * @param index
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	void autoMove(int index) throws SQLException, InterruptedException;

	/**
	 * Get the status of a player
	 *
	 */
	boolean playerStatus();

	/**
	 * Getter  of Win
	 *
	 */
	boolean getWin();

	/**
	 * Setter of Win
	 * @param win
	 */
	void setWin(boolean win);

	/**
	 * Setter of Diamond Counter
	 * @param diamondCounter
	 */
	void setDiamondCounter(int diamondCounter);

}
