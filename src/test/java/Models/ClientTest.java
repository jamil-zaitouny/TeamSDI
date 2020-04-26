package Models;

import springjpa.Model.Client;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class ClientTest {
    private static final int ID = 1;
    private static final String NAME = "Dadi";

    private Client client;

    @Before
    public void setup()throws Exception{
        client = new Client(ID, NAME);
    }

    @After
    public void tearDown() throws Exception{
        client = null;
    }

    @Test
    public void testGetClientID() throws Exception{
        Assert.assertEquals( "The ID should be, ",(Integer) ID, client.getId());
    }
    @Test
    public void testSetClientID() throws Exception{
        client.setId(2);
        Assert.assertEquals( "The ID should be", client.getId(), (Integer) 2);
    }

    @Test
    public void testGetClientName() throws Exception{
        Assert.assertEquals("The name should be",NAME, client.getName());
    }
    @Test
    public void testSetClientName() throws Exception{
        client.setName("Veronica");
        Assert.assertEquals("The ID should be",client.getName(),"Veronica" );
    }

}
