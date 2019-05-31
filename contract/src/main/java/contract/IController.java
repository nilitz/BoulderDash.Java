package contract;

import java.sql.SQLException;

/**
 * The Interface IController.
 *
 * @author Hugo Degrossi
 */
public interface IController {

	/**
	 *  Control.
	 */
	public void control();

	/**
	 * Perfom the order gotten from user.
	 *
	 * @param controllerOrder
	 *          the controller order
	 */
	public void orderPerform(ControllerOrder controllerOrder) throws SQLException;
}
