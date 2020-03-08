package InMemoryRepositoryTests;

import Model.Book;
import Model.Client;
import Model.Purchase;
import Repository.RepositoryInMemory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PurchaseInMemoryRepositoryTests {
    private RepositoryInMemory purchases;
    private RepositoryInMemory clients;
    private RepositoryInMemory books;

    @Before
    public void setUp() {
        clients=new RepositoryInMemory();
        clients.add(new Client(1,"a"));
        clients.add(new Client(2,"a"));

        books=new RepositoryInMemory();
        books.add(new Book("1234567890098","a","a", "A"));
        books.add(new Book("1234567890078","b","b", "a"));
        books.add(new Book("1334567890078","c","c", "b"));

        purchases=new RepositoryInMemory();
        purchases.add(new Purchase(1,"1234567890098",1));
        purchases.add(new Purchase(2,"1234567890078",2));
        purchases.add(new Purchase(3,"1334567890078",1));
    }

    @After
    public void tearDown() throws Exception {
        clients=null;
        books=null;
        purchases=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Purchase(1,"1234567890098",1),purchases.findOne(1).get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(purchases.add(new Purchase(7,"1234567890798",2)));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(purchases.delete(2));
    }
}
