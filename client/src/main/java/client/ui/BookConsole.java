package client.ui;

import core.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.dto.BookDto;
import web.dto.BooksDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookConsole extends DefaultConsole {
    public static final String URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    private static final int PrintBooksOption = 1;
    private static final int AddBookOption = 2;
    private static final int DeleteBookOption = 3;
    private static final int UpdateBookOption = 4;
    private static final int FilterByGenre = 5;


    public BookConsole(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
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

        BooksDto allBooks=restTemplate.getForObject(URL+"/books/filter?genre={genre}",BooksDto.class,genre);
        List<BookDto> books=allBooks.getBooks();
        books.forEach(System.out::println);
    }

    private void updateBook() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("IBSN: ");
        String id = bufferRead.readLine();
        System.out.println("New Title: ");
        String title = bufferRead.readLine();
        System.out.println("New Author: ");
        String author = bufferRead.readLine();
        System.out.println("New genre: ");
        String genre = bufferRead.readLine();

        BookDto bookDto= new BookDto(title,author,genre);
        bookDto.setId(id);

        try {
            restTemplate.put(URL+"/books/{id}" , bookDto,id);
        }
        catch (RestClientException e)
        {
            System.out.println("Can't update");
        }
    }

    private void addBook() throws IOException {
        System.out.println("Read book {IBSN, title, author, genre}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        String ibsn = bufferRead.readLine();
        String title = bufferRead.readLine();
        String author = bufferRead.readLine();
        String genre = bufferRead.readLine();

        BookDto bookDto= new BookDto(title,author,genre);
        bookDto.setId(ibsn);

        try {
            restTemplate.postForObject(URL+"/books" , bookDto, BookDto.class);
        }
        catch (RestClientException e)
        {
            System.out.println("Can't add");
        }
    }

    private void deleteBook() throws IOException {
        System.out.println("IBSN: ");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String ibsn = bufferedReader.readLine();

        restTemplate.delete(URL+"/books/{id}",ibsn);

        //this.purchaseControllercontroller.deleteAllPurchasesForBook(ibsn);
    }

    private void printBooks()
    {
        BooksDto allBooks=restTemplate.getForObject(URL+"/books",BooksDto.class);
        List<BookDto> books=allBooks.getBooks();
        books.forEach(System.out::println);
    }

    @Override
    public void displayMenu() {
        System.out.println("Options: ");
        System.out.println("\t1.Print books");
        System.out.println("\t2.Add book");
        System.out.println("\t3.Delete book");
        System.out.println("\t4.Update book");
        System.out.println("\t5.Filter by genre");
        System.out.println("\t0.Go back");
    }
}
