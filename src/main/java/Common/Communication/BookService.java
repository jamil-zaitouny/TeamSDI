package Common.Communication;

import Model.Book;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface BookService {
    CompletableFuture<Set<Book>> print_books();
    CompletableFuture<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre);
    CompletableFuture<Void> deleteBook(String ISBN);
    CompletableFuture<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre);
    CompletableFuture<Book> searchByIsbn(String ISBN);
    CompletableFuture<Set<Book>> filterByGenre(String genre);
    CompletableFuture<Iterable<Book>> sortBooksByTitleAuthor();
}
