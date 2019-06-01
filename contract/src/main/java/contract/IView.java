package contract;

import java.awt.*;

/**
 * The Interface IView.
 *
 * @author De Grossi Hugo
 */
public interface IView {

	/**
	 * Prints the message.
	 * @param message
	 * the message to print
	 */
	void printMessage(final String message);

	/**
	 * Close the frame and end the program
	 */
	void closeFrame();

}
