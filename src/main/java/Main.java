import Controller.*;
import Model.Book;
import Model.Client;
import Model.Purchase;

import Repository.DBRepository.BookDBRepository;
import Repository.DBRepository.ClientDBRepository;
import Repository.DBRepository.PurchaseDBRepository;
import Repository.FileRepositories.BookFileRepository;
import Repository.FileRepositories.ClientFileRepository;
import Repository.FileRepositories.PurchaseFileRepository;
import Repository.RepositoryInterface;
import Repository.SortRepository.SortingRepository;
import Repository.XMLRepositories.BookXMLRepository;
import Repository.XMLRepositories.ClientXMLRepository;
import Repository.XMLRepositories.PurchaseXMLRepository;
import Ui.Console;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        String isbn = "1234567890999";
        String title = "Fram, ursul polar";
        String author = "Cezar Petrescu";
        String genre = "fiction";

        String directory = ".\\src\\main\\java\\Files\\";
        String xmlDirectory = ".\\src\\main\\java\\Files\\";
        Book book = new Book(isbn, title, author, genre);

        SortingRepository<Integer, Purchase> purchaseRepo = new PurchaseDBRepository();
        SortingRepository<String, Book> bookRepo = new BookDBRepository();
        SortingRepository<Integer, Client> clientRepo = new ClientDBRepository();

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);
        PurchaseController purchaseController=new PurchaseController(purchaseRepo,clientController,bookController);

        Console console = new Console(clientController, bookController,purchaseController);
        console.run();
    }
}
