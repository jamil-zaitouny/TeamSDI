package Ui;

import Controller.BookController;
import Model.Book;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookConsole extends DefaultConsole{
    public BookController controller;

    private static final int PrintBooksOption = 1;
    private static final int AddBookOption = 2;


    public BookConsole(BookController controller) {
        this.controller = controller;
    }

    @Override
    public int dealChoice(int choice) throws IOException {
        switch (choice) {
            case PrintBooksOption:
                printBooks();
                break;
            case AddBookOption:
                try {
                    addBook();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                break;
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void addBook() throws IOException, TransformerException, ParserConfigurationException {
        this.controller.addBook(readBook());
    }

    private void printBooks() {
        this.controller.getAllBooks().forEach(System.out::println);
    }

    private Book readBook() {
        System.out.println("Read book {IBSN, title, author}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String ibsn = bufferRead.readLine();
            String title = bufferRead.readLine();
            String author = bufferRead.readLine();

            return new Book(ibsn, title, author);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print books");
        System.out.println("\t2.Add book");
        System.out.println("\t0.Go back");
    }
}
