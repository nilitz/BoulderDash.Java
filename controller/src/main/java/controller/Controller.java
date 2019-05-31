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
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}

	/**
	 * this print messages with the controls
	 *
	 */
	public void control() {
		this.view.printMessage("----- Controls -----" + "\n" + "| UP : Z | DOWN : S | LEFT : Q | RIGHT : D |" + "\n" + "-----Credits -----" + "\n" + " Group 8 ");
	}

	/**
	 * Print the message when game is over
	 */
	public void death(){
		this.view.printMessage("GAME OVER");
	}

	/**
	 * Print the message when Won
	 */
	public void win(){this.view.printMessage("WINNER WINNER CHICKEN DINNER");
	}
	/**
     * Sets the view.
     *
     * @param pview
     *            the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Set the game on
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void play() throws InterruptedException, SQLException, IOException {
		int i = 1;
		control();
		while(i == 1){
			i = autoMoveController();
			orderPerform(ControllerOrder.NOTHING);
			if (i == 0){death();}
			if (i == 2){
				win();
				nextLevel();
				i = 1;
			}
		}
	}

	/**
	 * Initiate a new level
	 * @throws IOException
	 * @throws SQLException
	 */
	public void nextLevel() throws IOException, SQLException {
		this.model.setDiamondCounter(0);
		this.model.setWin(false);
		this.model.setID(this.model.getID() + 1);
		this.model.setMap(this.model.getID());
	}

	/**
	 * Control the autoMove of the enemy or the diamond
	 * @return
	 * @throws InterruptedException
	 * @throws SQLException
	 * @throws IOException
	 */
	public int autoMoveController() throws InterruptedException, SQLException, IOException {
		for (int index = 361 - 1; index >= 0; index--){
			this.model.autoMove(index);
		}
		this.setView(view);
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
	 * Perform the order given by the model.
	 * @param controllerOrder
	 * @throws SQLException
	 * @throws IOException
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
