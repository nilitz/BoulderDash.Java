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
     * Control.
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("----- Controls -----" + "\n" + "| UP : Z | DOWN : S | LEFT : Q | RIGHT : D |" + "\n" + "-----Credits -----" + "\n" + " Group 8 ");
	}
	public void death(){
		this.view.printMessage("GAME OVER");
	}
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

	/*
	public void start(int niveau) throws SQLException, InterruptedException {

		loop(niveau);
	}

	public void loop(int niveau) throws InterruptedException, SQLException {
		while (!this.model.playerStatus() || !this.model.getWin()){
			for (int index = 361 - 1; index >= 0; index--){
				this.model.autoMove(index);

			}
			this.setView(view);
			this.orderPerform(ControllerOrder.NOTHING);
			Thread.sleep(150);
		}
		if (this.model.getWin()){
			this.model.setDiamondCounter(0);
			this.win();
			this.model.setID(this.model.getID() + 1);
			this.start(this.model.getID());
		}
		else{
			this.death();
		}
	}

	public void close(){}


	 */
	public int autoMove() throws InterruptedException, SQLException {
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
	 * Order perform.
	 *
	 * @param controllerOrder
	 *            the controller order
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) throws SQLException {

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
