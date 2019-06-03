package entity;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * TEST | The Class ObjectTest
 *
 * @author De Grossi Hugo And Girardi Guillaume
 */
public class ObjectTest {

    /**
     * Name Getter And Setter
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void getAndSetName() throws IOException {
        Rock rock = new Rock("FIRST", true, LastMove.NOTHING, 0);
        assertEquals("FIRST", rock.getName());
        rock.setName("SECOND");
        assertEquals("SECOND", rock.getName());
    }

    /**
     * Status Getter And Setter
     * @throws IOException
     * throws Image related exception
     */
    @Test
    public void setAndGetStatus() throws IOException {
        Rock rock = new Rock("FIRST", true, LastMove.NOTHING, 0);
        assertTrue(rock.getStatus());
        rock.setStatus(false);
        assertFalse(rock.getStatus());
    }


    /**
     * LastMove Getter And Setter
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void getAndSetLastMove() throws IOException {
        Rock rock = new Rock("FIRST", true, LastMove.NOTHING, 0);
        assertEquals(LastMove.NOTHING, rock.getLastMove());
        rock.setLastMove(LastMove.DOWN);
        assertEquals(LastMove.DOWN, rock.getLastMove());
    }

    /**
     * LastIndex Getter And Setter
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void setAndGetLastIndex() throws IOException {
        Rock rock = new Rock("FIRST", true, LastMove.NOTHING, 0);
        rock.setLastIndex(10);
        assertEquals(10, rock.getLastIndex());
    }


    /**
     * ResourcesPack Getter And Setter
     * @throws IOException
     * throws image related exception
     */
    @Test
    public void setAndGetResourcesPack() throws IOException {
        Rock rock = new Rock("FIRST", true, LastMove.NOTHING, 0);
        rock.setResourcesPack("EXPECTED");
        assertEquals("EXPECTED", rock.getResourcesPack());
    }


}