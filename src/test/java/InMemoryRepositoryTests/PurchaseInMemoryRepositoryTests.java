package InMemoryRepositoryTests;

import Model.Book;
import Model.Client;
import Model.Purchase;
import Repository.RepositoryInMemory;
import Repository.RepositoryInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PurchaseInMemoryRepositoryTests {
    private RepositoryInterface purchases;
    private RepositoryInterface clients;
    private RepositoryInterface books;

    @Before
    public void setUp() throws IOException {
        clients=new RepositoryInMemory();
        clients.add(new Client(1,"a"));
        clients.add(new Client(2,"a"));

        books=new RepositoryInMemory();
        books.add(new Book("1234567890098","a","a", "A"));
        books.add(new Book("1234567890078","b","b", "a"));
        books.add(new Book("1334567890078","c","c", "b"));

        purchases=new RepositoryInMemory();
        purchases.add(new Purchase(1,"1234567890098",1,"a"));
        purchases.add(new Purchase(2,"1234567890078",2,"a"));
        purchases.add(new Purchase(3,"1334567890078",1,"a"));
    }

    @After
    public void tearDown() throws Exception {
        clients=null;
        books=null;
        purchases=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Purchase(1,"1234567890098",1,"a"),purchases.findOne(1).get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(purchases.add(new Purchase(7,"1234567890798",2,"a")));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(purchases.delete(2));
    }
}
