package bookshop.core.service;

import bookshop.core.model.Book;
import bookshop.core.model.Exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IBookService {
    public void addBook(Book newBook);
    public void addBook(String IBSN, String title, String author, String genre);
    public void deleteBook(String ibsn);
    public void updateBook(String ibsn, String newTitle, String newAuthor, String genre);
    public Book searchByIbsn(String ibsn);
    public Optional findOne(String  ISBN);
    public List<Book> getAllBooks();
    public Set<Book> filterByGenre(String genre);
    public Iterable<Book> sortBooksByTitleAuthor();
}
