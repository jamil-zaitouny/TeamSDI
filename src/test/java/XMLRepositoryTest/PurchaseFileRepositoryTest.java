package XMLRepositoryTest;

import Model.Book;
import Model.Client;
import Model.Purchase;
import Repository.FileRepositories.BookFileRepository;
import Repository.FileRepositories.ClientFileRepository;
import Repository.FileRepositories.PurchaseFileRepository;
import Repository.RepositoryInterface;
import Repository.XMLRepositories.BookXMLRepository;
import Repository.XMLRepositories.ClientXMLRepository;
import Repository.XMLRepositories.PurchaseXMLRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PurchaseFileRepositoryTest {
    private RepositoryInterface purchases;
    private RepositoryInterface clients;
    private RepositoryInterface books;
    private String directory = ".\\src\\main\\java\\Files\\";
    @Before
    public void setUp() throws Exception {
        clients=new ClientXMLRepository(directory,"ClientTest.xml");
        clients.add(new Client(1,"a"));
        clients.add(new Client(2,"a"));

        books=new BookXMLRepository(directory,"BookTest.xml");
        books.add(new Book("1234567890098","a","a", "bla"));
        books.add(new Book("1234567890078","b","b", "bla"));
        books.add(new Book("1334567890078","c","c", "bla"));

        purchases=new PurchaseXMLRepository(directory,"PurchaseTest.xml");
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
