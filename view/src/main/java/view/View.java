package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The Class View.
 *
 * @author Girardi Guillaume And De Grossi Hugo
 */
public final class View implements IView, Runnable {

	/** The frame. */
	private final ViewFrame viewFrame;

	/**
	 * Instantiates a new view.
	 * @param model
	 * the model
	 * @throws IOException
	 * throws image related exception
	 */
	public View(final IModel model) throws IOException {
		this.viewFrame = new ViewFrame(model);
		SwingUtilities.invokeLater(this);
	}

	/**
	 * Key code to controller order.
	 *
	 * @param keyCode
	 * the key code
	 * @return the controller order
	 */
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode){
		switch (keyCode) {
			case KeyEvent.VK_Z:
				return ControllerOrder.UP;
			case KeyEvent.VK_Q:
				return ControllerOrder.LEFT;
			case KeyEvent.VK_S:
				return ControllerOrder.DOWN;
			case KeyEvent.VK_D:
				return ControllerOrder.RIGHT;
			default:
				return ControllerOrder.NOTHING;
		}
	}




	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IView#printMessage(java.lang.String)
	 */
	public void printMessage(final String message) {
		this.viewFrame.printMessage(message);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.viewFrame.setVisible(true);
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller
	 * the new controller
	 */
	public void setController(final IController controller) {
		this.viewFrame.setController(controller);
	}

	public ViewFrame getViewFrame() {
		return viewFrame;
	}

	public String getCode(){
		return this.viewFrame.getCode();
	}
	public void setCode(String code){
		this.viewFrame.setCode(code);
	}

	public void closeFrame(){
		this.viewFrame.dispose();
	}
}
