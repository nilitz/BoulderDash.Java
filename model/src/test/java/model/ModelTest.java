/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModelTest {
    private Model model;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.model = new Model();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMessage() {
        Assert.assertEquals("", this.model.getHelloWorld().getMessage());
    }

    /**
     * Test method for {@link model.Model#loadHelloWorld(java.lang.String)}.
     */
    @Test
    public void testGetMessageString() {
        this.model.loadHelloWorld("GB");
        Assert.assertEquals("Hello world", this.model.getHelloWorld().getMessage());
        this.model.loadHelloWorld("FR");
        Assert.assertEquals("Bonjour le monde", this.model.getHelloWorld().getMessage());
        this.model.loadHelloWorld("DE");
        Assert.assertEquals("Hallo Welt", this.model.getHelloWorld().getMessage());
        this.model.loadHelloWorld("ID");
        Assert.assertEquals("Salamat pagi dunia", this.model.getHelloWorld().getMessage());
    }
}
