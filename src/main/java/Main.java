import Controller.*;
import Model.Book;
import Model.Client;
import Model.Validators.BookValidator;
import Model.Validators.ClientValidator;

import Repository.RepositoryInMemory;
import Ui.Console;

public class Main {
    public static void main(String[] args) {

        Book book = new Book("1234567890999", "Fram, ursul polar", "Cezar Petrescu");

        ClientValidator clientValidator = new ClientValidator();
        BookValidator bookValidator = new BookValidator();

        RepositoryInMemory<Integer, Client> clientRepo = new RepositoryInMemory<>(clientValidator);
        RepositoryInMemory<String, Book> bookRepo = new RepositoryInMemory<>(bookValidator);

        ClientController clientController = new ClientController(clientRepo);
        BookController bookController = new BookController(bookRepo);

        bookController.addBook(book);

        Console console = new Console(clientController, bookController);
        console.run();
    }
}
