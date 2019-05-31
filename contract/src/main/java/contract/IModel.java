package contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import entity.MapTile;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel {

	ArrayList<MapTile> getMap();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();

	void setMap(int ID) throws SQLException, IOException;

	int getID();

	void setID(int ID);

	int[] getSize() throws SQLException;

	int getMapWidth() throws SQLException;

    int getMapHeight() throws SQLException;

	void loadPlayerControl(ControllerOrder controllerOrder);

	void movement(String direction) throws SQLException, IOException;

	void enemyAutoMove(int index) throws SQLException, InterruptedException, IOException;

	void autoMove(int index) throws SQLException, InterruptedException, IOException;

	boolean playerStatus();

	boolean getWin();

	void setWin(boolean win);

	void setDiamondCounter(int diamondCounter);

	int getDiamondToHave();

	int getDiamondCounter();

	int getDiamondNumber(int ID) throws SQLException;

	void setDiamondToHave(int diamondToHave);

}