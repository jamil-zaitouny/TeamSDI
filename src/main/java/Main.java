import Controller.*;
import Model.Book;
import Model.Client;
import Model.Purchase;

import Repository.FileRepositories.BookFileRepository;
import Repository.FileRepositories.ClientFileRepository;
import Repository.FileRepositories.PurchaseFileRepository;
import Repository.RepositoryInterface;
import Ui.Console;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        String directory = "C:\\Users\\jamil\\Desktop\\TeamSDI\\src\\main\\java\\Files\\";
        Book book = new Book("1234567890999", "Fram, ursul polar", "Cezar Petrescu");

        RepositoryInterface<Integer, Purchase> purchaseRepo = new PurchaseFileRepository(directory);
        RepositoryInterface<String, Book> bookRepo = new BookFileRepository(directory);
        RepositoryInterface<Integer, Client> clientRepo = new ClientFileRepository(directory);

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);
        PurchaseController purchaseController=new PurchaseController(purchaseRepo,clientRepo,bookRepo);

        bookController.addBook(book);

        Console console = new Console(clientController, bookController,purchaseController);
        console.run();
    }
}
