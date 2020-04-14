package Server.serviceimplementation;

import Common.Communication.BookService;
import Controller.BookController;
import Model.Book;
import Repository.SortRepository.SortingRepository;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@ComponentScan("Repository.DBRepository")
public class BookServiceImplementation implements BookService {
    SortingRepository<String, Book> bookRepository;
    BookController bookController;
    public BookServiceImplementation(SortingRepository<String, Book> bookRepository){
        this.bookRepository = bookRepository;
        this.bookController = new BookController(bookRepository);
    }

    @Override
    public Set<Book> print_books() {
        return this.bookController.getAllBooks();
    }

    @Override
    public void addBook(String ISBN, String newTitle, String newAuthor, String genre) {
        try {
            this.bookController.addBook(ISBN, newTitle, newAuthor, genre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String ISBN) {
        this.bookController.deleteBook(ISBN);
    }

    @Override
    public void updateBook(String ISBN, String newTitle, String newAuthor, String genre) {
        this.bookController.updateBook(ISBN, newTitle, newAuthor, genre);
    }

    @Override
    public Book searchByIsbn(String ISBN) {
        Optional<Book> book = this.bookController.findOne(ISBN);
        return book.get();
    }

    @Override
    public Set<Book> filterByGenre(String genre) {
        return this.bookController.filterByGenre(genre);
    }

    @Override
    public Iterable<Book> sortBooksByTitleAuthor() {
        return this.bookController.sortBooksByTitleAuthor();
    }

    @Override
    public Optional findOne(String ISBN) {
        return this.bookController.findOne(ISBN);
    }

    @Override
    public Set<Book> getAllBooks() {
        return this.bookController.getAllBooks();
    }
}
