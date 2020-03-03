package Ui;

import Controller.*;

public class Console extends DefaultConsole{
    private ClientController clientController;
    private BookController bookController;

    private static final int BooksOption = 1;
    private static final int ClientsOption = 2;

    public Console(ClientController clientController, BookController bookController) {
        this.clientController = clientController;
        this.bookController = bookController;
    }

    @Override
    public int dealChoice(int choice) {
        switch (choice) {
            case BooksOption:
                dealBooks();
                break;
            case ClientsOption:
                dealClients();
                break;
            case ExitOption:
                return -1;
            default:
                break;
        }
        return 0;
    }

    private void dealClients() {
        ClientConsole clientConsole = new ClientConsole(clientController);
        clientConsole.run();
    }

    private void dealBooks() {
        BookConsole bookConsole = new BookConsole(bookController);
        bookConsole.run();
    }

    @Override
    public void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Books ");
        System.out.println("\t2.Clients ");
        System.out.println("\t0.Exit ");
        System.out.println("Choose: ");
    }
}
