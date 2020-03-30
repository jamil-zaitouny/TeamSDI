package Common.HandlerServices;

import Model.Book;
import java.util.Set;
import java.util.concurrent.Future;

public interface BookControllerService{
    String PRINT_BOOKS = "printBooks";
    String ADD_BOOK = "addBook";
    String DELETE_BOOK = "removeBook";
    String UPDATE_BOOK = "updateBook";
    String SEARCH_BY_ISBN = "searchForBook";
    String FILTER_BY_GENRE = "filterByGenre";

    Future<Set<Book>> print_books();
    Future<Void> add_book(String ISBN, String newTitle, String newAuthor, String genre);
    Future<Void> delete_book(String ISBN);
    Future<Void> update_book(String ISBN, String newTitle, String newAuthor, String genre);
    Future<Book> search_by_isbn(String ISBN);
    Future<Set<Book>> filter_by_genre();
}
