import Controller.*;
import Model.Book;
import Model.Client;
import Model.Purchase;

import Repository.FileRepositories.BookFileRepository;
import Repository.FileRepositories.ClientFileRepository;
import Repository.FileRepositories.PurchaseFileRepository;
import Repository.RepositoryInterface;
import Ui.Console;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        String isbn = "1234567890999";
        String title = "Fram, ursul polar";
        String author = "Cezar Petrescu";
        String genre = "fiction";

        String directory = ".\\src\\main\\java\\Files\\";

        RepositoryInterface<Integer, Purchase> purchaseRepo = new PurchaseFileRepository(directory);
        RepositoryInterface<String, Book> bookRepo = new BookFileRepository(directory);
        RepositoryInterface<Integer, Client> clientRepo = new ClientFileRepository(directory);

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);
        PurchaseController purchaseController=new PurchaseController(purchaseRepo,clientController,bookController);

        bookController.addBook(isbn, title, author, genre);

        Console console = new Console(clientController, bookController,purchaseController);
        console.run();
    }
}
