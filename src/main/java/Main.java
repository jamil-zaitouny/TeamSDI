import Controller.BookController;
import Controller.ClientController;
import Model.Book;
import Model.Client;
import Model.Validators.BookValidator;
import Model.Validators.ClientValidator;
import Repository.BookRepositoryInMemmory;
import Repository.ClientRepositoryInMemory;
import Ui.Console;

public class Main {
    public static void main(String[] args) {

        Book book = new Book("1234567890999", "Fram, ursul polar", "Cezar Petrescu");

        ClientValidator clientValidator = new ClientValidator();
        BookValidator bookValidator = new BookValidator();

        ClientRepositoryInMemory<Integer, Client> clientRepo = new ClientRepositoryInMemory<>(clientValidator);
        BookRepositoryInMemmory<String, Book> bookRepo = new BookRepositoryInMemmory<>(bookValidator);

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);

        bookController.addBook(book);

        Console console = new Console(clientController, bookController);
        console.run();
    }
}
