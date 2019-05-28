package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import org.graalvm.compiler.loop.InductionVariable;

import java.util.ArrayList;

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
		this.view.printMessage("Appuyer sur les touches 'E', 'F', 'D' ou 'I', pour afficher Hello world dans la langue d votre choix.");
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
	ArrayList <String> Map = new ArrayList<>();

	public void moveUpDown(int index){
		if (mapTiles.get(index).getName().equals("Ground_One") || mapTiles.get(index).getName().equals("Ground_Two")){
			mapTiles.get(index + this.direction).setName("Player_One");
			mapTiles.get(index).setName("Ground_Two");
		}
	}


	public void moveLeftRight(int index){
		if (mapTiles.get(index + this.direction).getName().equals("Rock")){
			if (mapTiles.get(index + (2 * this.direction)).getName().equals("Ground_Two")){
				mapTiles.get(index + this.direction).setName("Player_One");
				mapTiles.get(index + (2 * this.direction)).setName("Rock");
				mapTiles.get(index).setName("Ground_Two");

			}

		}
		else if (mapTiles.get(index).getName().equals("Ground_One") || mapTiles.get(index).getName().equals("Ground_Two")){
			mapTiles.get(index + this.direction).setName("Player_One");
			mapTiles.get(index).setName("Ground_Two");
		}

	}

	public void orderPerform(final ControllerOrder controllerOrder) {
		for (int i = 0; i <= Map.size(); i++){
			if (Map.get(i).getName()=="Player"){
				Direction direction;
				switch (controllerOrder) {
					case Up:
						direction = Direction.Up;
						break;
					case Down:
						direction = Direction.Down;
						break;
					case Left:
						direction = Direction.Left;
						break;
					case Right:
						direction = Direction.Right;
						break;
					case Nothing:
						direction = Direction.Nothing;
						break;

					}
				}
			}
		}
	}

}
