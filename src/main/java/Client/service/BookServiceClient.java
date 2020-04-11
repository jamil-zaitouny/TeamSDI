package Client.service;
import Common.Communication.BookService;
import Common.Communication.ClientService;
import Model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BookServiceClient{

    BookService bookService;

    public BookServiceClient(BookService bookService) {
        this.bookService = bookService;
    }

    public CompletableFuture<Set<Book>> print_books() {
        return CompletableFuture.supplyAsync(()->bookService.print_books());
    }

    public CompletableFuture<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return CompletableFuture.runAsync(()->{bookService.addBook(ISBN,newTitle,newAuthor,genre);});
    }

    public CompletableFuture<Void> deleteBook(String ISBN) {
        return CompletableFuture.runAsync(()->{bookService.deleteBook(ISBN);});
    }

    public CompletableFuture<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre) {
        return CompletableFuture.runAsync(()->bookService.updateBook(ISBN,newTitle,newAuthor,genre));
    }

    public CompletableFuture<Book> searchByIsbn(String ISBN) {
        return CompletableFuture.supplyAsync(()->bookService.searchByIsbn(ISBN));
    }

    public CompletableFuture<Set<Book>> filterByGenre(String genre) {
        return CompletableFuture.supplyAsync(()->bookService.filterByGenre(genre));
    }

    public CompletableFuture<Iterable<Book>> sortBooksByTitleAuthor() {
            return CompletableFuture.supplyAsync(()->bookService.sortBooksByTitleAuthor());
    }

    public CompletableFuture<Optional> findOne(String ISBN) {
                return CompletableFuture.supplyAsync(()->bookService.findOne(ISBN));
    }

    public CompletableFuture<Set<Book>> getAllBooks() {
                    return CompletableFuture.supplyAsync(()->bookService.getAllBooks());
    }
}
