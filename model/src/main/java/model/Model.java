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
	private int ID = 1;
	private ControllerOrder controllerOrder;
	private int indexPlayer;
	private int direction;
	private Object temporaryObject;
	private int diamondToHave = 0;
	private int diamondCounter = 0;
	private boolean win = false;

	/**
	 * Instantiates a new model.
	 */
	public Model() throws SQLException, IOException {
		this.map = DAO.getMapSql(ID);
	}

	/**
	 * Swapping methods
	 */
	public void swap(int depart, int arrive) {
		Object temp = this.map.get(arrive).getObject();
		this.map.get(arrive).setObject(map.get(depart).getObject());
		this.map.get(depart).setObject(temp);
	}
	public void swapToGroundTwo(int depart, int arrive) throws IOException {
		Object temp = this.map.get(arrive).getObject();
		this.map.get(arrive).setObject(map.get(depart).getObject());
		this.map.get(depart).setObject(temp);
		changeObjectToGroundTwo(depart);
	}
	public void changeObjectToGroundTwo(int index) throws IOException {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Ground_Two ground_two = new Ground_Two("Ground_Two", false, LastMove.NOTHING);
			this.map.get(index).setObject(ground_two);
		}
	}
	public void changeObjectToDiamond(int index) throws IOException {
		if (!this.map.get(index).getObject().getName().equals("Wall_One")){
			Diamond diamond = new Diamond("Diamond", false, LastMove.NOTHING);
			this.map.get(index).setObject(diamond);
		}
	}

	/**
	 * Status Methods
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
	 * Locating Methods
	 */
	public boolean checkFalling(int index){
		return this.map.get(index).getObject().getStatus();
	}
	public void locatePlayer(){
		for(int incr = 0; incr < map.size(); incr++){
			if (this.map.get(incr).getObject().getName().equals("Player_One") && !this.map.get(incr).getObject().getStatus()){
				this.indexPlayer = incr;
			}
		}
	}
	public String playerNextCase(){
		return this.map.get(this.indexPlayer + this.direction).getObject().getName();

	}
	public int rockSearch(int i){
		for (int index = i ; index < this.map.size(); index++){
			if(this.map.get(index).getObject().getName().equals("Rock")){
				return index;
			}
		}
		return 404;
	}
	public String nextCase(int index, int direction){
		return this.map.get(index + direction).getObject().getName();
	}
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
	public void changeLastMove(int index, LastMove lastMove){
		this.map.get(index).getObject().setLastMove(lastMove);
	}

	/**
	 * Moving Player
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

	/**
	 * Moving Enemy
	 */
	public void enemyAutoMove(int index) throws SQLException, InterruptedException, IOException {
		switch((int)(Math.random() * 5)){
			case(1):
				moveEnemy(index, -1);
				changeLastMove(index - 1, LastMove.LEFT);
				break;
			case(2):
				moveEnemy(index, - getMapWidth());
				changeLastMove(index - getMapWidth(), LastMove.UP);
				break;
			case(3):
				moveEnemy(index, 1);
				changeLastMove(index + 1, LastMove.RIGHT);
				break;
			case(4):
				moveEnemy(index, getMapWidth());
				changeLastMove(index + getMapWidth(), LastMove.DOWN);
				break;
			default:
				break;
		}
	}

	/**
	 * Moving Diamond / Rock
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
		else if(this.map.get(index).getObject().getName().equals("Enemy_One")){
			enemyAutoMove(index);
		}
	}
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
	public void moveRock() throws SQLException, IOException {
		if (this.map.get(this.indexPlayer + 2 * this.direction).getObject().getName().equals("Ground_Two") && (this.direction == 1 || this.direction == -1) ){
			movePlayer();
		}
	}
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
	 * Loader
	 */
	public void loadPlayerControl(final ControllerOrder controllerOrder){
		this.setControllerOrder(controllerOrder);
	}

	/**
	 * WIP Methods
	 */
	public void playerDie(){}
	public void gameWin(){
		setWin(true);
		setDiamondCounter(0);
	}
	public void collectDiamond(){
		this.diamondCounter++;
		if (this.diamondCounter == this.diamondToHave){
			gameWin();

		}
	}

	/**
	 * Getter / Setter
	 */
	public int getMapWidth() throws SQLException {
		int[] temporary = getSize();
		return temporary[1];
	}
	public int getMapHeight() throws SQLException {
		int[] temporary = getSize();
		return temporary[0];
	}
	public void setID(int num) { this.ID = num; }
	public int getID() { return this.ID; }
	public int[] getSize() throws SQLException { return DAO.getMapSize(this.ID); }
	public ArrayList<MapTile> getMap() { return this.map; }
	public void setMap(int ID) throws SQLException, IOException { this.map = DAO.getMapSql(ID); }
	public Observable getObservable() { return this; }
	public void setControllerOrder(ControllerOrder controllerOrder) { this.controllerOrder = controllerOrder; }
	public ControllerOrder getControllerOrder() { return controllerOrder; }
	public void setWin(boolean win) { this.win = win; }
	public boolean getWin(){ return this.win; }
	public void setDiamondToHave(int diamondToHave) { this.diamondToHave = diamondToHave; }
	public void setDiamondCounter(int diamondCounter) { this.diamondCounter = diamondCounter; }
	public int getDiamondCounter() { return this.diamondCounter; }
	public int getDiamondToHave() { return this.diamondToHave; }
	public int getDiamondNumber(int ID) throws SQLException{ return DAO.getDiamondNumber(ID); }
}