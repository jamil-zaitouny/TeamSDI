package Common.Communication;

import Model.Book;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface BookService {
    Set<Book> print_books();
    void addBook(String ISBN, String newTitle, String newAuthor, String genre);
    void deleteBook(String ISBN);
    void updateBook(String ISBN, String newTitle, String newAuthor, String genre);
    Book searchByIsbn(String ISBN);
    Set<Book> filterByGenre(String genre);
    Iterable<Book> sortBooksByTitleAuthor();
    Optional findOne(String  ISBN);
    Set<Book> getAllBooks();
}
