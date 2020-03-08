import Controller.*;
import Model.Book;
import Model.Client;
import Model.Purchase;
import Model.Validators.BookValidator;
import Model.Validators.ClientValidator;

import Model.Validators.PurchaseValidator;
import Repository.RepositoryInMemory;
import Ui.Console;

public class Main {
    public static void main(String[] args) {

        String isbn = "1234567890999";
        String title = "Fram, ursul polar";
        String author = "Cezar Petrescu";
        String genre = "fiction";

        RepositoryInMemory<Integer, Client> clientRepo = new RepositoryInMemory<>();
        RepositoryInMemory<String, Book> bookRepo = new RepositoryInMemory<>();
        RepositoryInMemory<Integer, Purchase> purchaseRepo = new RepositoryInMemory<>();

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);
        PurchaseController purchaseController=new PurchaseController(purchaseRepo,clientRepo,bookRepo);

        bookController.addBook(isbn, title, author, genre);

        Console console = new Console(clientController, bookController,purchaseController);
        console.run();
    }
}
