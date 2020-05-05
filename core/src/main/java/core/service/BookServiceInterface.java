package core.service;

import core.model.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookServiceInterface {

    void addBook(Book book) ;
    void deleteBook(String ibsn);
    void updateBook(Book book);
    Optional<Book> findOne(String  ISBN);
    List<Book> getAllBooks() ;
    List<Book> filterByGenre(String genre);
}
