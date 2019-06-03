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

	/** The ArrayList map*/
	private ArrayList<MapTile> map;
	/** instantiate a new DAOMap*/
	private DAOMap DAO = new DAOMap(DBConnection.getInstance().getConnection());
	/** The map ID*/
	private int ID = 4;  //MODEL TESTS ONLY WORK WITH THE MAP N°4 ALL THE TESTS ARE DONE WITH THIS MAP SO DON'T CHANGE IT IF YOU WANT TO RUN THE TESTS
	/** the Controller Order*/
	private ControllerOrder controllerOrder;
	/** the player index in the ArrayList*/
	private int indexPlayer;
	/** the direction*/
	private int direction;
	/** the temporary Object*/
	private Object temporaryObject;
	/** the number of diamond to have*/
	private int diamondToHave = 0;
	/** the diamond the player have already collected*/
	private int diamondCounter = 0;
	/** the boolean win*/
	private boolean win = false;
	/** the boolean isMoveable (player)*/
	private boolean isMoveable = true;

	/**
	 * WHY THE MODEL IS SO LONG ?
	 * @apiNote
	 * Globally, All the methods are in the model, and there's ~350 lines of javadoc, sorry :/
	 */


	/**
	 * Model constructor
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	public Model() throws SQLException, IOException {
		this.map = DAO.getMapSql(ID);
	}


	/**
	 * Swap two Objects between two positions
	 * @param start
	 * start position of the Object in the ArrayList
	 * @param finish
	 * finish position of the Object in the ArrayList
	 */
	void swap(int start, int finish) {
		Object temp = this.map.get(finish).getObject();
		this.map.get(finish).setObject(map.get(start).getObject());
		this.map.get(start).setObject(temp);
	}

	/**
	 * Swap start case to Ground_Two, used while player moves
	 * @param start
	 * start position of the Object in the ArrayList
	 * @param finish
	 * finish position of the Object in the ArrayList
	 */
	void swapToGroundTwo(int start, int finish) {
		swap(start, finish);
		changeObjectToGroundTwo(start);
	}

	/**
	 * Change an Object to Ground_Two at the indicated index
	 * @param index
	 * index of the object to change in the ArrayList
	 */
	void changeObjectToGroundTwo(int index) {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Ground_Two ground_two = new Ground_Two("Ground_Two", false, LastMove.NOTHING, -1);
			this.map.get(index).setObject(ground_two);
		}
	}

	/**
	 * Change an Object to Diamond at the indicated index
	 * @param index
	 * index of the object to change in the ArrayList
	 */
	void changeObjectToDiamond(int index)  {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Diamond diamond = new Diamond("Diamond", false, LastMove.NOTHING, -1);
			this.map.get(index).setObject(diamond);
		}
	}

	/**
	 * Check if Player Status is True or False
	 * @return
	 * return true : playerIsDead | false : playerIsAlive
	 */
	public boolean playerStatus(){
		for (MapTile mapTile : this.map) {
			if (mapTile.getObject().getName().equals("Player_One")) {
				return mapTile.getObject().getStatus();
			}
		}
		return true;

	}


	/**
	 * Check if Object Status is True or False, only used for Diamonds and Rocks
	 * @param index
	 * index of the position to check in the ArrayList
	 * @return
	 * return true : isFalling | false : isStatic
	 */
	boolean checkFalling(int index){
		return this.map.get(index).getObject().getStatus();
	}

	/**
	 * Locate the player in the ArrayList
	 */
	void locatePlayer(){
		for(int incr = 0; incr < map.size(); incr++){
			if (this.map.get(incr).getObject().getName().equals("Player_One") && !this.map.get(incr).getObject().getStatus()){
				this.indexPlayer = incr;
			}
		}
	}

	/**
	 * Return the type of the next case of the player according to the KeyListener
	 * @return
	 * return the name of the next player Case
	 */
	String playerNextCase(){
		return this.map.get(this.indexPlayer + this.direction).getObject().getName();
	}

	/**
	 * Return the type of the next case
	 * @param index
	 * index of the actual position in the ArrayList
	 * @param direction
	 * index of the direction
	 * @return
	 * return the name of the next case (actual position + direction)
	 */
	String nextCase(int index, int direction){
		return this.map.get(index + direction).getObject().getName();
	}

	/**
	 * Look around a case and return a code
	 * @param index
	 * Index of the actual position in the ArrayList
	 * @param direction
	 * direction where to look at (int)
	 * @return
	 * return a code according to the nextCase() method
	 */
	int aroundCode(int index, int direction){
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
	 * index of the moving object in the ArrayList
	 * @param lastMove
	 * direction of the move
	 */
	void changeLastMove(int index, LastMove lastMove){
		this.map.get(index).getObject().setLastMove(lastMove);
		this.map.get(index).getObject().setLastIndex(index);
	}

	/**
	 * Generate the movement of the Player according to the Controller entries
	 * @param direction
	 * direction indicated by the Controller
	 * @throws SQLException
	 * throws sql related exception
	 */
	public void movement(String direction) throws SQLException {
		if(getMoveable()){
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
	}

	/**
	 * Check Player Next Case and decide if he can move / die / collect Diamond / do Nothing
	 */
	void switchArray() {
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
	 * Combine swapping methods to move the player
	 */
	void movePlayer() {
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
	 * index of the actual position in the ArrayList
	 * @param direction
	 * direction where to move
	 * @return
	 * return a boolean if the enemy move (true) or not (false)
	 */
	boolean moveEnemy(int index, int direction) {
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


	/**
	 * method to check and move the enemy if he can - RIGHT
	 * @param index
	 * index of the actual position in the ArrayList
	 * @return
	 * return true : he moved | false : he can't move
	 */
	private boolean moveEnemyRight(int index) {
		if(moveEnemy(index, 1)){
			changeLastMove(index + 1, LastMove.RIGHT);
			return true;
		}
		return false;
	}

	/**
	 * method to check and move the enemy if he can - DOWN
	 * @param index
	 * index of the actual position in the ArrayList
	 * @return
	 * return true : he moved | false : he can't move
	 * @throws SQLException
	 * throws sql related exception
	 */
	private boolean moveEnemyDown(int index) throws SQLException {
		if(moveEnemy(index, getMapWidth())){
			changeLastMove(index + getMapWidth(), LastMove.DOWN);
			return true;
		}
		return false;
	}
	/**
	 * method to check and move the enemy if he can - LEFT
	 * @param index
	 * index of the actual position in the ArrayList
	 * @return
	 * return true : he moved | false : he can't move
	 */
	private boolean moveEnemyLeft(int index) {
		if(moveEnemy(index, - 1)){
			changeLastMove(index - 1, LastMove.LEFT);
			return true;
		}
		return false;
	}
	/**
	 * method to check and move the enemy if he can - UP
	 * @param index
	 * index of the actual position in the ArrayList
	 * @return
	 * return true : he moved | false : he can't move
	 * @throws SQLException
	 * throws sql related exception
	 */
	private boolean moveEnemyUp(int index) throws SQLException {
		if(moveEnemy(index, - getMapWidth())){
			changeLastMove(index - getMapWidth(), LastMove.UP);
			return true;
		}
		return false;
	}




	/**
	 * Enemy AI (MOVING) | Check Every Moving Case according to the last move
	 * @param index
	 * index of the actual position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	public void enemyAutoMove(int index) throws SQLException {
		if(this.map.get(index).getObject().getLastIndex() != index){
			switch (this.map.get(index).getObject().getLastMove()){
				case RIGHT:
					moveEnemyLastMoveRight(index);
					break;
				case LEFT:
					moveEnemyLastMoveLeft(index);
					break;
				case UP:
					moveEnemyLastMoveUp(index);
					break;
				case DOWN:
					moveEnemyLastMoveDown(index);
					break;
			}
		} else {
			this.map.get(index).getObject().setLastIndex(-1);
		}
	}


	/**
	 * Rocks and Diamonds auto-fall and Call enemyautomove
	 * @param index
	 * index of the actual position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	public void autoMove(int index) throws SQLException {
		if (nextCase(index, 0).equals("Rock") || nextCase(index, 0).equals("Diamond")){
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
						//whereToMove(index); // YOU CAN ACTIVATE IT IF YOU WANT AN OTHER GAMEPLAY WITH ROCKS AND DIAMONDS SLIDING ON GROUND_ONE ENTITIES
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
			/* //YOU CAN DELETE THE COMMENTARY SO THE DIAMONDS AND ROCKS AUTOMATICALLY FALL WHEN THERE'S A DIAMOND / ROCK UNDER THEM
			else if("Diamond".equals(nextCase(index, getMapWidth())) || "Rock".equals(nextCase(index, getMapWidth()))){
				whereToMove(index);
			}
			 */
		} else if(nextCase(index, 0).equals("Enemy_One")){
			enemyAutoMove(index);
		}
	}

	/**
	 * Generate an explosion of diamonds
	 * @param index
	 * index of the position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	void diamondsTNT(int index) throws SQLException {
		for (int i = index - 1;i <= index + 1; i ++){
			changeObjectToDiamond(i);
		}
		for (int i = index + getMapWidth() - 1;i <= index + getMapWidth() + 1; i ++){
			changeObjectToDiamond(i);
		}
		for (int i = index + 2 * getMapWidth() - 1;i <= index + 2 * getMapWidth() + 1; i ++){
			changeObjectToDiamond(i);
		}
	}
	/**
	 * Check if a player can move a Rock (and move it if he can)
	 */
	void moveRock() {
		if (nextCase(this.indexPlayer + 2 * this.direction, 0).equals("Ground_Two") && (this.direction == 1 || this.direction == -1) ){
			movePlayer();
		}
	}


	/**
	 * Choose if a Rock / Diamond can slip on a block / slip kill an enemy or player
	 * @param index
	 * index of the position in the ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	void whereToMove(int index) throws SQLException {
		if ((nextCase(index - 1, 0).equals("Ground_Two") && nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two")) || ((nextCase(index - 1, 0).equals("Player_One") || nextCase(index + getMapWidth() - 1, 0).equals("Player_One")) && (nextCase(index - 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two"))) || ((nextCase(index - 1, 0).equals("Enemy_One") || nextCase(index + getMapWidth() - 1, 0).equals("Enemy_One")) && (nextCase(index - 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() - 1, 0).equals("Ground_Two"))) ) {

			if(nextCase(index, - 1).equals("Enemy_One") || nextCase(index, - getMapWidth()).equals("Enemy_One") ){
				diamondsTNT(index - getMapWidth());
			}
			else if(nextCase(index, - 1).equals("Player_One") || nextCase(index,  - getMapWidth()).equals("Player_One")){
				locatePlayer();
				diamondsTNT(index - getMapWidth());
				this.map.get(this.indexPlayer).getObject().setStatus(true);
			}
			swap(index, index + getMapWidth() - 1);
			this.map.get(index).getObject().setStatus(true);
		}
		else if((nextCase(index + 1, 0).equals("Ground_Two") && nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two")) || ((nextCase(index + 1, 0).equals("Player_One") && nextCase(index + getMapWidth() + 1, 0).equals("Player_One")) && (nextCase(index + 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two"))) || ((nextCase(index + 1, 0).equals("Enemy_One") && nextCase(index + getMapWidth() + 1, 0).equals("Enemy_One")) && (nextCase(index + 1, 0).equals("Ground_Two") || nextCase(index + getMapWidth() + 1, 0).equals("Ground_Two"))) ) {

			if(nextCase(index,  1).equals("Player_One") || nextCase(index,  getMapWidth()).equals("Player_One")){
				locatePlayer();
				diamondsTNT(index + getMapWidth());
				this.map.get(this.indexPlayer).getObject().setStatus(true);

			} else if(nextCase(index, 1).equals("Enemy_One") || nextCase(index, getMapWidth()).equals("Enemy_One") ){
				diamondsTNT(index + getMapWidth());
			}
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
	 * ControllerOrder enum
	 */
	public void loadPlayerControl(final ControllerOrder controllerOrder){
		this.setControllerOrder(controllerOrder);
	}

	/**
	 * move the enemy if his last move is on the right
	 * @param index
	 * index of the enemy position in ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	private void moveEnemyLastMoveRight(int index) throws SQLException {
		if(!moveEnemyRight(index)){
			if(!moveEnemyDown(index)){
				if(!moveEnemyLeft(index)){
					moveEnemyUp(index);
				}
			}
		}
	}
	/**
	 * move the enemy if his last move is on the left
	 * @param index
	 * index of the enemy position in ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	private void moveEnemyLastMoveLeft(int index) throws SQLException {
		if(!moveEnemyLeft(index)){
			if(!moveEnemyUp(index)){
				if(!moveEnemyRight(index)){
					moveEnemyDown(index); } } }
	}
	/**
	 * move the enemy if his last move is on the up
	 * @param index
	 * index of the enemy position in ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */

	private void moveEnemyLastMoveUp(int index) throws SQLException {
		if(!moveEnemyUp(index)){
			if(!moveEnemyRight(index)){
			if(!moveEnemyDown(index)){
				moveEnemyLeft(index); } } } }

	/**
	 * move the enemy if his last move is on the down
	 * @param index
	 * index of the enemy position in ArrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	private void moveEnemyLastMoveDown(int index) throws SQLException {
		if(!moveEnemyDown(index)){
			if(!moveEnemyLeft(index)){
				if(!moveEnemyUp(index)){
					moveEnemyRight(index); } } } }


	/**
	 * set Win to true and restart the diamond counter
	 */
	void gameWin(){
		setWin(true);
		setDiamondCounter(0);
	}

	/**
	 * Add a diamond to the counter and check if the value is reached
	 */
	void collectDiamond(){
		this.diamondCounter++;
		if (this.diamondCounter == this.diamondToHave){ gameWin(); }
	}


	/**
	 * ArrayMap Width (number of case) Getter
	 * @return
	 * return the width of the arrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	public int getMapWidth() throws SQLException {
		int[] temporary = getSize();
		return temporary[1];
	}

	/**
	 * ArrayMap Height (number of case) Getter
	 * @return
	 * return the height of the arrayList
	 * @throws SQLException
	 * throws sql related exception
	 */
	public int getMapHeight() throws SQLException {
		int[] temporary = getSize();
		return temporary[0];
	}

	/**
	 * Map ID Setter
	 * @param num
	 * ID of the Map - for DB import
	 */
	public void setID(int num) { this.ID = num; }

	/**
	 * Map ID Getter
	 * @return
	 * return the ID of the Map - for DB import
	 */
	public int getID() { return this.ID; }

	/**
	 * Getter SizeMap
	 * @return
	 * return the size of the map (number of case width x number of case height)
	 * @throws SQLException
	 * throws sql related exception
	 */
	public int[] getSize() throws SQLException { return DAO.getMapSize(this.ID); }

	/**
	 *  ArrayList of Maptiles Getter
	 * @return
	 * return the map arraylist
	 */
	public ArrayList<MapTile> getMap() { return this.map; }

	/**
	 *  ArrayList of Maptiles (launch DAO.getMapSQL(ID)) Setter
	 * @param ID
	 * ID of the Map | for DB
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	public void setMap(int ID) throws SQLException, IOException { this.map = DAO.getMapSql(ID); }

	/**
	 * Observable Getter
	 * @return
	 * return this
	 */
	public Observable getObservable() { return this; }

	/**
	 * ControllerOrder Setter
	 * @param controllerOrder
	 * ControllerOrder enum
	 */
	void setControllerOrder(ControllerOrder controllerOrder) { this.controllerOrder = controllerOrder; }
	/**
	 * ControllerOrder Getter
	 * @return Controller Order
	 */
	ControllerOrder getControllerOrder() {
		return controllerOrder;
	}

	/**
	 * Win Setter (true = win)
	 * @param win
	 * boolean : true = WIN | false : NOT WIN YET
	 */
	public void setWin(boolean win) { this.win = win; }

	/**
	 * Win Getter
	 * @return
	 * return true if win
	 */
	public boolean getWin(){ return this.win; }

	/**
	 * Diamonds Goal Setter
	 * @param diamondToHave
	 * diamonds to have from the DB
	 */
	public void setDiamondToHave(int diamondToHave) { this.diamondToHave = diamondToHave; }

	/**
	 * Diamonds Counter Setter
	 * @param diamondCounter
	 * diamonds counter, set to 0 at every launch of a new map
	 */
	public void setDiamondCounter(int diamondCounter) { this.diamondCounter = diamondCounter; }

	/**
	 * Diamonds Counter Getter
	 * @return
	 * return the diamondCounter
	 */
	public int getDiamondCounter() { return this.diamondCounter; }

	/**
	 * Diamonds Goal Getter
	 * @return
	 * return the number of diamonds to have
	 */
	public int getDiamondToHave() { return this.diamondToHave; }

	/**
	 * Diamonds Number Getter
	 * @param ID
	 * ID of the map | DB related
	 * @return
	 * return the diamond number to have in the map
	 * @throws SQLException
	 * throws sql related exception
	 */
	public int getDiamondNumber(int ID) throws SQLException{ return DAO.getDiamondNumber(ID); }

	/**
	 * isMoveable Setter
	 * @param moveable
	 * boolean moveable : if true = player is Moveable | false = player is not Moveable
	 */
	public void setMoveable(boolean moveable) { isMoveable = moveable; }

	/**
	 * isMoveable Getter
	 * @return
	 * return if the player is moveable (true) or not (false)
	 */
	private boolean getMoveable(){ return this.isMoveable; }

	/**
	 * indexPlayer Getter
	 * @return
	 * return the index of the player
	 */
	int getIndexPlayer() {
		return indexPlayer;
	}

	/**
	 * Direction Setter
	 * @param direction
	 * direction
	 */
	void setDirection(int direction) { this.direction = direction; }
}