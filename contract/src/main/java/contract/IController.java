package contract;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Interface IController.
 *
 * @author De Grossi Hugo
 */
public interface IController {

	/**
	 * Print a control message at the start
	 */
	void control();

	/**
	 * launch model method according to the player entries
	 * @param controllerOrder
	 * the controller order
	 * @throws SQLException
	 * throws sql related exception
	 * @throws IOException
	 * throws image related exception
	 */
	void orderPerform(ControllerOrder controllerOrder) throws SQLException, IOException;

	int autoMoveController() throws InterruptedException, SQLException, IOException;


}
