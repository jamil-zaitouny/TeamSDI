package Ui;

import Controller.BookController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookConsole extends DefaultConsole {
    public BookController controller;

    private static final int PrintBooksOption = 1;
    private static final int AddBookOption = 2;
    private static final int DeleteBookOption = 3;
    private static final int UpdateBookOption = 4;
    private static final int SearchByIbsnOption = 5;
    private static final int FilterByGenre = 6;


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
                addBook();
                break;
            case DeleteBookOption:
                deleteBook();
                break;
            case UpdateBookOption:
                updateBook();
                break;
            case SearchByIbsnOption:
                searchByIbsnBook();
            case FilterByGenre:
                filterByGenre();
            case ExitOption:
                return -1;
            default:
                System.out.println("Wrong option! Try again!");
                break;
        }
        return 0;
    }

    private void filterByGenre() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Genre: ");
        String genre= bufferRead.readLine();

        this.controller.filterByGenre(genre).forEach(System.out::println);
    }

    private void searchByIbsnBook() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("IBSN: ");
        String ibsn = bufferRead.readLine();

        System.out.println(this.controller.searchByIbsn(ibsn));
    }

    private void updateBook() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("IBSN: ");
        String ibsn = bufferRead.readLine();
        System.out.println("New Title: ");
        String title = bufferRead.readLine();
        System.out.println("New Author: ");
        String author = bufferRead.readLine();
        System.out.println("New genre: ");
        String genre = bufferRead.readLine();

        this.controller.updateBook(ibsn, title, author, genre);
    }

    private void addBook() throws IOException {
        System.out.println("Read book {IBSN, title, author, genre}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        String ibsn = bufferRead.readLine();
        String title = bufferRead.readLine();
        String author = bufferRead.readLine();
        String genre = bufferRead.readLine();
        this.controller.addBook(ibsn, title, author, genre);
    }

    private void deleteBook() throws IOException {
        System.out.println("IBSN: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String ibsn = bufferedReader.readLine();

        this.controller.deleteBook(ibsn);
    }

    private void printBooks() {
        this.controller.getAllBooks().forEach(System.out::println);
    }

    @Override
    public void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print books");
        System.out.println("\t2.Add book");
        System.out.println("\t3.Delete book");
        System.out.println("\t4.Update book");
        System.out.println("\t5.Search by ibsn");
        System.out.println("\t6.Filter by genre");
        System.out.println("\t0.Go back");
    }
}
