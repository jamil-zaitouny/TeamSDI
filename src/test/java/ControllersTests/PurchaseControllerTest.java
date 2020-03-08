package ControllersTests;

import Controller.BookController;
import Controller.ClientController;
import Controller.PurchaseController;
import Model.Book;
import Model.Client;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.BookValidator;
import Model.Validators.ClientValidator;
import Model.Validators.PurchaseValidator;
import Repository.RepositoryInMemory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class PurchaseControllerTest
{
    private PurchaseController purchaseController;
    private ClientController clientController;
    private BookController bookController;

    @Before
    public void setUp() throws IOException {

        RepositoryInMemory<Integer, Client> clientRepo = new RepositoryInMemory<>();
        RepositoryInMemory<String, Book> bookRepo = new RepositoryInMemory<>();
        RepositoryInMemory<Integer, Purchase> purchaseRepo = new RepositoryInMemory<>();

        clientController = new ClientController(clientRepo);
        bookController = new BookController(bookRepo);
        purchaseController=new PurchaseController(purchaseRepo,clientRepo,bookRepo);

        clientController.addClient(new Client(1,"a"));
        clientController.addClient(new Client(2,"a"));

        bookController.addBook(new Book("9781234567897","a","a"));
        bookController.addBook(new Book("2781234567897","b","b"));

        purchaseController.addPurchase(new Purchase(1,"9781234567897",1));
        purchaseController.addPurchase(new Purchase(2,"2781234567897",2));
    }

    @After
    public void tearDown() {
        purchaseController=null;
        clientController=null;
        bookController=null;
    }

    @Test
    public void addPurchaseTest() throws IOException {
        this.purchaseController.addPurchase(new Purchase(3,"2781234567897",2));
    }

    @Test
    public void getAllTest()   {
        Set<Purchase> purchases= this.purchaseController.getAllPurchases();
        Assert.assertEquals(2, purchases.size());
    }
}
