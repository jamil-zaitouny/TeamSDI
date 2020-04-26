package XMLRepositoryTest;

import springjpa.Model.Book;
import springjpa.Model.Client;
import springjpa.Model.Purchase;
import springjpa.Repository.RepositoryInterface;
import springjpa.Repository.XMLRepositories.BookXMLRepository;
import springjpa.Repository.XMLRepositories.ClientXMLRepository;
import springjpa.Repository.XMLRepositories.PurchaseXMLRepository;
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
        purchases.add(new Purchase(1,"1234567890098",1, "bla"));
        purchases.add(new Purchase(2,"1234567890078",2, "bla"));
        purchases.add(new Purchase(3,"1334567890078",1, "bla"));
    }

    @After
    public void tearDown() throws Exception {
        clients=null;
        books=null;
        purchases=null;
    }

    @Test
    public void testFindOne() throws Exception {

        assertEquals("Failed",new Purchase(1,"1234567890098",1,"bla"),purchases.findOne(1).get());
    }

    @Test
    public void testAdd() throws Exception {
        assertNotNull(purchases.add(new Purchase(7,"1234567890798",2,"bla")));
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(purchases.delete(2));
    }
}
