package Common.HandlerServices;

import Model.Book;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface BookControllerServiceServer {
    String PRINT_BOOKS = "printBooks";
    String ADD_BOOK = "addBook";
    String DELETE_BOOK = "removeBook";
    String UPDATE_BOOK = "updateBook";
    String SEARCH_BY_ISBN = "searchForBook";
    String FILTER_BY_GENRE = "filterByGenre";

    Future<Set<Book>> print_books();
    Future<Void> addBook(String ISBN, String newTitle, String newAuthor, String genre);
    Future<Void> deleteBook(String ISBN);
    Future<Void> updateBook(String ISBN, String newTitle, String newAuthor, String genre);
    Future<Book> searchByIsbn(String ISBN);
    Future<Set<Book>> filterByGenre(String genre);
   Future<Iterable<Book>> sortBooksByTitleAuthor();
}
