package main;

import contract.ControllerOrder;
import contract.IView;
import controller.Controller;
import model.Model;
import org.junit.Test;
import view.View;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * TEST | Main Test
 *
 * @author De Grossi Hugo
 */
public class MainTest {

    /**
     * TEST | Controller
     * @throws IOException
     * throws image related exception
     * @throws SQLException
     * throws sql related exception
     */
    @Test
    public void controller() throws IOException, SQLException{
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(view, model);
        view.setController(controller);
        //TEST IF THE PLAYER DOESN'T MOVE (START INDEX = 20)
        controller.orderPerform(ControllerOrder.NOTHING);
        assertEquals("Player_One", model.getMap().get(20).getObject().getName());

        //TEST IF THE PLAYER GO DOWN
        controller.orderPerform(ControllerOrder.DOWN);
        assertEquals("Player_One", model.getMap().get(39).getObject().getName());

        //TEST IF THE PLAYER GO RIGHT 2X
        controller.orderPerform(ControllerOrder.RIGHT);
        controller.orderPerform(ControllerOrder.RIGHT);
        assertEquals("Player_One", model.getMap().get(41).getObject().getName());

        //TEST IF THE PLAYER GO LEFT 2X
        controller.orderPerform(ControllerOrder.LEFT);
        controller.orderPerform(ControllerOrder.LEFT);
        assertEquals("Player_One", model.getMap().get(39).getObject().getName());

        //TEST IF THE PLAYER GO UP
        controller.orderPerform(ControllerOrder.UP);
        assertEquals("Player_One", model.getMap().get(20).getObject().getName());
    }

    /**
     * TEST | Test the view
     * @throws IOException
     * throws the image exception
     * @throws SQLException
     * throws the sql exception
     * @throws InterruptedException
     * throws the thread exception
     */
    @Test
    public void view() throws IOException, SQLException, InterruptedException {
        //THE MVC
        final Model model = new Model();
        View view = new View(model);
        final Controller controller = new Controller(view, model);

        view.setController(controller);

        //CHECK BEFORE MOVING IF IMAGE ARE CHARGED
        assertEquals("", view.getCode());

        //SAVING THE PREVIOUS CODE
        String unexpected = view.getCode();

        //MOVING THE ENEMY
        controller.autoMoveController();

        //RE-CHECKING WITH A assertNOTequals, SHOULD DISPLAY A DIFFERENT MAP CODE
        assertNotEquals(unexpected, view.getCode());


    }
}