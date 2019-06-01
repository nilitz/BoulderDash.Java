package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView		view;

	/** The model. */
	private IModel	model;


	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *  the view
	 * @param model
	 *  the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}


	/**
	 * Print a control message at the start
	 */
	public void control() {
		this.view.printMessage("----- Controls -----" + "\n" + "| UP : Z | DOWN : S | LEFT : Q | RIGHT : D |" + "\n" + "-----Credits -----" + "\n" + " Group 8 ");
	}

	/**
     * Sets the view.
     * @param pview
     * the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 * the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}


	/**
	 * Play method, used in the main to start the game
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 * @throws InterruptedException
	 * throws thread related exception
	 */
	public void play() throws InterruptedException, SQLException, IOException {
		int i = 1;
		control();
		while(i == 1){
			i = autoMoveController();
			orderPerform(ControllerOrder.NOTHING);
			if(i == 1){
			}
			if (i == 2){
				nextLevel();
				i = 1;
			}
		}
	}

	/**
	 * used to change the level when the actual level is finished
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 * @throws InterruptedException
	 * throws thread related exception
	 */
	private void nextLevel() throws IOException, SQLException, InterruptedException {
		this.model.setMoveable(false);
		Thread.sleep(1000);
		if (this.model.getID() < 5){
			this.model.setID(this.model.getID() + 1);
		}else {
			this.model.setID(1);
		}
		this.model.setDiamondCounter(0);
		this.model.setWin(false);
		this.model.setMap(this.model.getID());
		this.model.setMoveable(true);
	}

	/**
	 * auto move the enemy and the rocks / diamonds
	 * @return
	 * return a different "int" value if : thep player is dead / the player win / none of the two
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 * @throws InterruptedException
	 * throws thread related exception
	 */
	private int autoMoveController() throws InterruptedException, SQLException, IOException {
		for (int index = (this.model.getMapWidth() * this.model.getMapHeight()) - 1; index >= 0; index--){
			this.model.autoMove(index);
			this.model.enemyAutoMove(index);
		}

		Thread.sleep(150);

		if(this.model.playerStatus()){
			return 0;
		}
		else if(this.model.getWin()){
			return 2;
		}
		else{
			return 1;
		}
	}


	/**
	 * launch model method according to the player entries
	 * @param controllerOrder
	 * the controller order
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	public void orderPerform(final ControllerOrder controllerOrder) throws SQLException, IOException {

		switch (controllerOrder) {
			case UP:
				this.model.movement("UP");
				break;
			case DOWN:
				this.model.movement("DOWN");
				break;
			case RIGHT:
				this.model.movement("RIGHT");
				break;
			case LEFT:
				this.model.movement("LEFT");
				break;
			case NOTHING:
				this.model.movement("NOTHING");
				break;
			default:
				break;
		}
		this.setView(view);
	}

}
