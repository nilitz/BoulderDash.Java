/**
 * @author De Grossi Hugo & Geraldi Guillaume
 * @version 1.4
 */
package main;

import controller.Controller;
import model.Model;
import view.View;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The Class Main.
 *
 * @author De Grossi Hugo
 */
public abstract class Main {


    /**
     * MAIN
     * @param args
     * the main method
     * @throws SQLException
     * throws sql related exception
     * @throws IOException
     * throws image related exception
     * @throws InterruptedException
     * throws thread related exception
     */
    public static void main(final String[] args) throws SQLException, IOException, InterruptedException {
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(view, model);
        view.setController(controller);

        controller.play();
        Thread.sleep(1000);
        view.closeFrame();
    }
}