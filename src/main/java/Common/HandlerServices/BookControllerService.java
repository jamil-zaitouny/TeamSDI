package Common.HandlerServices;

import Model.Book;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface BookControllerService{
    String PRINT_BOOKS = "printBooks";
    String ADD_BOOK = "addBook";
    String DELETE_BOOK = "removeBook";
    String UPDATE_BOOK = "updateBook";
    String SEARCH_BY_ISBN = "searchForBook";
    String FILTER_BY_GENRE = "filterByGenre";

    CompletableFuture<Set<Book>> print_books();
    CompletableFuture<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre);
    CompletableFuture<Void> deleteBook(String ISBN);
    CompletableFuture<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre);
    CompletableFuture<Book> searchByIsbn(String ISBN);
    CompletableFuture<Set<Book>> filterByGenre(String genre);
    CompletableFuture<Iterable<Book>> sortBooksByTitleAuthor();
}
