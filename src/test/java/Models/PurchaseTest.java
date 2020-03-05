package Models;

import Model.Purchase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PurchaseTest {
    private final static int id=1;
    private final static int clientId=1;
    private final static String bookID="9781234567897";

    private Purchase purchase;

    @Before
    public void setup()throws Exception{
        purchase=new Purchase(id,bookID,clientId);
    }

    @After
    public void tearDown() throws Exception{
        purchase = null;
    }

    @Test
    public void testGetBookId() throws Exception
    {
        Assert.assertEquals("Book id should be", bookID,purchase.getBookId());
    }
    @Test
    public void testGetClientId() throws Exception
    {
        Assert.assertEquals("Client id should be", clientId,purchase.getClientId());
    }
    @Test
    public void testGetId() throws Exception
    {
        Assert.assertEquals("Purchase id should be", (Integer) id,purchase.getId());
    }

    @Test
    public void testSetBookId() throws Exception
    {
        purchase.setBookId("1234567890098");
        Assert.assertEquals("Book id should be", "1234567890098",purchase.getBookId());
    }
    @Test
    public void testSetClientId() throws Exception
    {
        purchase.setClientId(3);
        Assert.assertEquals("Client id should be", 3,purchase.getClientId());
    }
    @Test
    public void testSetId() throws Exception
    {
        purchase.setId(7);
        Assert.assertEquals("Purchase id should be", (Integer)7,purchase.getId());
    }

}
