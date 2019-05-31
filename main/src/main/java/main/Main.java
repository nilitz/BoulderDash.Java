/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package main;

import contract.ControllerOrder;
import controller.Controller;
import model.Model;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The Class Main.
 *
 * @author HugoDegrossi
 */
public abstract class Main {

    /**
     * The main methods which runs the whole program
     * @param args
     * @throws SQLException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(final String[] args) throws SQLException, IOException, InterruptedException {
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(view, model);


        view.setController(controller);



        controller.play();
        view.closeFrame();





    }
}
