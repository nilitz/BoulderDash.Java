package model;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;


import contract.ControllerOrder;
import contract.IModel;
import entity.*;
import entity.Object;

/**
 * The Class Model.
 *
 * @author De Grossi Hugo
 */
public final class Model extends Observable implements IModel {

	/** The Variables */
	private ArrayList<MapTile> map;
	private DAOMap DAO = new DAOMap(DBConnection.getInstance().getConnection());
	private int ID = 2;
	private ControllerOrder controllerOrder;
	private int indexPlayer;
	private int direction;
	private Object temporaryObject;
	private int diamondToHave = 0;
	private int diamondCounter = 0;
	private boolean win = false;


	/**
	 * Model constructor
	 * @throws SQLException
	 * @throws IOException
	 */
	public Model() throws SQLException, IOException {
		this.map = DAO.getMapSql(ID);
	}


	/**
	 * Swap two Objects between two positions
	 * @param depart
	 * @param arrive
	 */
	public void swap(int depart, int arrive) {
		Object temp = this.map.get(arrive).getObject();
		this.map.get(arrive).setObject(map.get(depart).getObject());
		this.map.get(depart).setObject(temp);
	}

	/**
	 * Swap start case to Ground_Two, used while player moves
	 * @param depart
	 * @param arrive
	 * @throws IOException
	 */
	public void swapToGroundTwo(int depart, int arrive) throws IOException {
		Object temp = this.map.get(arrive).getObject();
		this.map.get(arrive).setObject(map.get(depart).getObject());
		this.map.get(depart).setObject(temp);
		changeObjectToGroundTwo(depart);
	}

	/**
	 * Change an Object to Ground_Two at the indicated index
	 * @param index
	 * @throws IOException
	 */
	public void changeObjectToGroundTwo(int index) throws IOException {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Ground_Two ground_two = new Ground_Two("Ground_Two", false, LastMove.NOTHING, -1);
			this.map.get(index).setObject(ground_two);
		}
	}

	/**
	 * Change an Object to Diamond at the indicated index
	 * @param index
	 * @throws IOException
	 */
	public void changeObjectToDiamond(int index) throws IOException {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Diamond diamond = new Diamond("Diamond", false, LastMove.NOTHING, -1);
			this.map.get(index).setObject(diamond);
		}
	}

	/**
	 * Check if Player Status is True or False
	 * @return
	 */
	public boolean playerStatus(){
		for(int i = 0; i < this.map.size(); i++){
			if(this.map.get(i).getObject().getName().equals("Player_One")){
				if (this.map.get(i).getObject().getStatus()){
					return true;
				}
				return false;
			}
		}
		return true;

	}


	/**
	 * Check if Object Status is True or False, only used for Diamonds & Rocks
	 * @param index
	 * @return
	 */
	public boolean checkFalling(int index){
		return this.map.get(index).getObject().getStatus();
	}

	/**
	 * Locate the player in the ArrayList
	 */
	public void locatePlayer(){
		for(int incr = 0; incr < map.size(); incr++){
			if (this.map.get(incr).getObject().getName().equals("Player_One") && !this.map.get(incr).getObject().getStatus()){
				this.indexPlayer = incr;
			}
		}
	}

	/**
	 * Return the type of the next case of the player according to the KeyListener
	 * @return
	 */
	public String playerNextCase(){
		return this.map.get(this.indexPlayer + this.direction).getObject().getName();

	}

	/**
	 * Return the type of the next case
	 * @param index
	 * @param direction
	 * @return
	 */
	public String nextCase(int index, int direction){
		return this.map.get(index + direction).getObject().getName();
	}

	/**
	 * Look around a case and return a code
	 * @param index
	 * @param direction
	 * @return
	 */
	public int aroundCode(int index, int direction){
		int codeReturn;
		switch (nextCase(index, direction)){
			case ("Ground_Two") :
				codeReturn = 0;
				return codeReturn;
			case ("Player_One") :
				codeReturn = 1;
				return codeReturn;
			default :
				codeReturn = 2;
				return codeReturn;
		}

	}

	/**
	 * Change the LastMove of an Object
	 * @param index
	 * @param lastMove
	 */
	public void changeLastMove(int index, LastMove lastMove){
		this.map.get(index).getObject().setLastMove(lastMove);
		this.map.get(index).getObject().setLastIndex(index);
	}

	/**
	 * Generate the movement of the Player according to the Controller entries
	 * @param direction
	 * @throws SQLException
	 * @throws IOException
	 */
	public void movement(String direction) throws SQLException, IOException {
		if (playerStatus()){direction = "NOTHING";}
		switch (direction) {
			case "UP":
				this.direction = - getMapWidth();
				switchArray();
				changeLastMove(this.indexPlayer, LastMove.UP);
				break;
			case "DOWN":
				this.direction = getMapWidth();
				switchArray();
				changeLastMove(this.indexPlayer, LastMove.DOWN);
				break;
			case "RIGHT":
				this.direction = 1;
				switchArray();
				changeLastMove(this.indexPlayer, LastMove.RIGHT);
				break;
			case "LEFT":
				this.direction = - 1;
				switchArray();
				changeLastMove(this.indexPlayer, LastMove.LEFT);
				break;
			default:
				break;
		}
		locatePlayer();

	}

	/**
	 * Check Player Next Case and decide if he can move / die / collect Diamond / do Nothing
	 * @throws SQLException
	 * @throws IOException
	 */
	public void switchArray() throws SQLException, IOException {
		if (playerNextCase().equals("Rock") && this.controllerOrder != ControllerOrder.UP && this.controllerOrder != ControllerOrder.DOWN){
			moveRock();
		}
		else if (playerNextCase().equals("Enemy_One")){
			playerStatus();
		}
		else if (playerNextCase().equals("Ground_Two") || playerNextCase().equals("Ground_One")){
			movePlayer();

		}
		else if (playerNextCase().equals("Diamond")){
			movePlayer();
			collectDiamond();
		}
		locatePlayer();
	}

	/**
	 * Combinate swapping methods to move the player
	 * @throws SQLException
	 * @throws IOException
	 */
	public void movePlayer() throws SQLException, IOException {
		locatePlayer();
		if (playerNextCase().equals("Ground_One") || playerNextCase().equals("Diamond") || playerNextCase().equals("Ground_Two")){
			swapToGroundTwo(this.indexPlayer, this.indexPlayer + this.direction);
		}
		else {
			swap(this.indexPlayer + this.direction,this.indexPlayer + 2 * this.direction);
			swapToGroundTwo(this.indexPlayer, this.indexPlayer + this.direction);
		}
	}

	/**
	 * Enemy move swapping method
	 * @param index
	 * @param direction
	 * @return
	 * @throws IOException
	 */
	public boolean moveEnemy(int index, int direction) throws IOException {
		switch(aroundCode(index, direction)){
			case(0):
				swap(index, index + direction);
				return true;
			case(1):
				swapToGroundTwo(index, index + direction);
				return true;
			default:
				return false;
			}

	}


	public boolean moveEnemyRight(int index) throws IOException {
		if(moveEnemy(index, 1)){
			changeLastMove(index + 1, LastMove.RIGHT);
			return true;
		}
		return false;
	}
	public boolean moveEnemyDown(int index) throws SQLException, IOException {
		if(moveEnemy(index, getMapWidth())){
			changeLastMove(index + getMapWidth(), LastMove.DOWN);
			return true;
		}
		return false;
	}
	public boolean moveEnemyLeft(int index) throws IOException {
		if(moveEnemy(index, - 1)){
			changeLastMove(index - 1, LastMove.LEFT);
			return true;
		}
		return false;
	}
	public boolean moveEnemyUp(int index) throws SQLException, IOException {
		if(moveEnemy(index, - getMapWidth())){
			changeLastMove(index - getMapWidth(), LastMove.UP);
			return true;
		}
		return false;
	}


	/**
	 * Enemy move AI
	 * @param index
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void enemyAutoMove(int index) throws SQLException, InterruptedException, IOException {
		if (this.map.get(index).getObject().getName().equals("Enemy_One")){
			if(this.map.get(index).getObject().getLastIndex() != index){
				switch (this.map.get(index).getObject().getLastMove()){
					case RIGHT:
						if(!moveEnemyRight(index)){
							if(!moveEnemyDown(index)){
								if(!moveEnemyLeft(index)){
									moveEnemyUp(index);
								}
							}
						}
						break;
					case LEFT:
						if(!moveEnemyLeft(index)){
							if(!moveEnemyUp(index)){
								if(!moveEnemyRight(index)){
									moveEnemyDown(index);
								}
							}
						}
						break;
					case UP:
						if(!moveEnemyUp(index)){
							if(!moveEnemyRight(index)){
								if(!moveEnemyDown(index)){
									moveEnemyLeft(index);
								}
							}
						}
						break;
					case DOWN:
						if(!moveEnemyDown(index)){
							if(!moveEnemyLeft(index)){
								if(!moveEnemyUp(index)){
									moveEnemyRight(index);
								}
							}
						}
						break;
				}
			} else {
				this.map.get(index).getObject().setLastIndex(-1);
			}
		}
	}


	/**
	 * Rocks and Diamonds auto-fall and Call enemyautomove
	 * @param index
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void autoMove(int index) throws SQLException, InterruptedException, IOException {
		if (this.map.get(index).getObject().getName().equals("Rock") || this.map.get(index).getObject().getName().equals("Diamond")){
			if (checkFalling(index)){
				switch(nextCase(index, getMapWidth())){
					case "Ground_Two":
						swap(index, index + getMapWidth());
						break;
					case "Player_One":
						this.map.get(index + getMapWidth()).getObject().setStatus(true);
						changeObjectToGroundTwo(index + getMapWidth());
						diamondsTNT(index);
						break;
					case "Ground_One":
						this.map.get(index).getObject().setStatus(false);
						whereToMove(index);
						break;
					case "Enemy_One":
						diamondsTNT(index);
						break;
					default:
						whereToMove(index);
						break;
				}
			}
			else if("Ground_Two".equals(nextCase(index, getMapWidth()))){
				this.map.get(index).getObject().setStatus(true);
			}
		}
	}

	/**
	 * Generate an explosion of diamonds
	 * @param index
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void diamondsTNT(int index) throws SQLException, InterruptedException, IOException {
		changeObjectToDiamond(index);
		Thread.sleep(50);
		changeObjectToDiamond(index + 1);
		Thread.sleep(50);
		changeObjectToDiamond(index - 1);
		Thread.sleep(50);
		changeObjectToDiamond(index + getMapWidth());
		Thread.sleep(50);
		changeObjectToDiamond(index + getMapWidth() - 1);
		Thread.sleep(50);
		changeObjectToDiamond(index + getMapWidth() + 1);
		Thread.sleep(50);
		changeObjectToDiamond(index + 2 * getMapWidth());
		Thread.sleep(50);
		changeObjectToDiamond(index + 2 * getMapWidth() - 1);
		Thread.sleep(50);
		changeObjectToDiamond(index + 2 * getMapWidth() + 1);
		Thread.sleep(50);}

	/**
	 * Check if a player can move a Rock (and move it if he can)
	 * @throws SQLException
	 * @throws IOException
	 */
	public void moveRock() throws SQLException, IOException {
		if (this.map.get(this.indexPlayer + 2 * this.direction).getObject().getName().equals("Ground_Two") && (this.direction == 1 || this.direction == -1) ){
			movePlayer();
		}
	}

	/**
	 * Rocks and Diamonds abilities to not doing a pile
	 * @param index
	 * @throws SQLException
	 */
	public void whereToMove(int index) throws SQLException {
		if ((nextCase(index - 1, 0).equals("Ground_Two") && nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two")) || ((nextCase(index - 1, 0).equals("Player_One") || nextCase(index + getMapWidth() - 1, 0).equals("Player_One")) && (nextCase(index - 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two"))) || ((nextCase(index - 1, 0).equals("Enemy_One") || nextCase(index + getMapWidth() - 1, 0).equals("Enemy_One")) && (nextCase(index - 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two"))) ) {
			swap(index, index + getMapWidth() - 1);
			this.map.get(index).getObject().setStatus(true);
		}
		else if((nextCase(index + 1, 0).equals("Ground_Two") && nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two")) || ((nextCase(index + 1, 0).equals("Player_One") && nextCase(index + getMapWidth() + 1, 0).equals("Player_One")) && (nextCase(index + 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two"))) || ((nextCase(index + 1, 0).equals("Enemy_One") && nextCase(index + getMapWidth() + 1, 0).equals("Enemy_One")) && (nextCase(index + 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two"))) ) {
			swap(index, index + getMapWidth() + 1);
			this.map.get(index).getObject().setStatus(true);
		}
		else {
			this.map.get(index).getObject().setStatus(false);
		}
	}

	/**
	 * set controller order
	 * @param controllerOrder
	 */
	public void loadPlayerControl(final ControllerOrder controllerOrder){
		this.setControllerOrder(controllerOrder);
	}

	/**
	 * set Win to true and restart the diamond counter
	 */

	public void gameWin(){
		setWin(true);
		setDiamondCounter(0);
	}

	/**
	 * Add a diamond to the counter and check if the value is reached
	 */
	public void collectDiamond(){
		this.diamondCounter++;
		if (this.diamondCounter == this.diamondToHave){
			gameWin();
		}
	}


	/**
	 * ArrayMap Width (number of case) Getter
	 * @return
	 * @throws SQLException
	 */
	public int getMapWidth() throws SQLException {
		int[] temporary = getSize();
		return temporary[1];
	}

	/**
	 * ArrayMap Height (number of case) Getter
	 * @return
	 * @throws SQLException
	 */
	public int getMapHeight() throws SQLException {
		int[] temporary = getSize();
		return temporary[0];
	}

	/**
	 * Map ID Setter
	 * @param num
	 */
	public void setID(int num) { this.ID = num; }

	/**
	 * Map ID Getter
	 * @return
	 */
	public int getID() { return this.ID; }

	/**
	 * Getter SizeMap
	 * @return
	 * @throws SQLException
	 */
	public int[] getSize() throws SQLException { return DAO.getMapSize(this.ID); }

	/**
	 *  ArrayList of Maptiles Getter
	 * @return
	 */
	public ArrayList<MapTile> getMap() { return this.map; }

	/**
	 *  ArrayList of Maptiles (launch DAO.getMapSQL(ID)) Setter
	 * @param ID
	 * @throws SQLException
	 * @throws IOException
	 */
	public void setMap(int ID) throws SQLException, IOException { this.map = DAO.getMapSql(ID); }

	/**
	 * Observable Getter
	 * @return
	 */
	public Observable getObservable() { return this; }

	/**
	 * ControllerOrder Setter
	 * @param controllerOrder
	 */
	public void setControllerOrder(ControllerOrder controllerOrder) { this.controllerOrder = controllerOrder; }

	/**
	 * Win Setter (true = win)
	 * @param win
	 */
	public void setWin(boolean win) { this.win = win; }

	/**
	 * Win Getter
	 * @return
	 */
	public boolean getWin(){ return this.win; }

	/**
	 * Diamonds Goal Setter
	 * @param diamondToHave
	 */
	public void setDiamondToHave(int diamondToHave) { this.diamondToHave = diamondToHave; }

	/**
	 * Diamonds Counter Setter
	 * @param diamondCounter
	 */
	public void setDiamondCounter(int diamondCounter) { this.diamondCounter = diamondCounter; }

	/**
	 * Diamonds Counter Getter
	 * @return
	 */
	public int getDiamondCounter() { return this.diamondCounter; }

	/**
	 * Diamonds Goal Getter
	 * @return
	 */
	public int getDiamondToHave() { return this.diamondToHave; }

	/**
	 * Diamonds Number Getter
	 * @param ID
	 * @return
	 * @throws SQLException
	 */
	public int getDiamondNumber(int ID) throws SQLException{ return DAO.getDiamondNumber(ID); }
}