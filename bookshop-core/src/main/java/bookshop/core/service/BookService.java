package bookshop.core.service;

import bookshop.core.model.Book;
import bookshop.core.model.Exceptions.ValidatorException;
import bookshop.core.model.Validators.IValidator;
import bookshop.core.repository.BookDBRepository;
import bookshop.core.repository.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{
    final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDBRepository repository;

    @Autowired
    private IValidator<Book> validator;

    public void addBook(Book book){
        this.addBook(book.getId(), book.getTitle(), book.getAuthorName(), book.getGenre());
    }

    public void addBook(String IBSN, String title, String author, String genre) throws ValidatorException {
        Book book = new Book(IBSN, title, author, genre);
        validator.validate(book);
        Optional<Book> previous=repository.findById(book.getId());
        previous.ifPresent(s -> {
            logger.info("ERROR: Try to add an existent book: " + IBSN);
            throw new ValidatorException("ID already exists.");
        });
        logger.info("Adding a new book: " + IBSN);
        repository.save(book);
    }

    public void deleteBook(String ibsn)
    {
        Optional<Book> previous=repository.findById(ibsn);
        previous.orElseThrow(() -> {
            logger.info("ERROR: Try to delete a non existent book: " + ibsn);
            throw new ValidatorException("Could not find book based on ID.");
        });
        logger.info("Deleting a book: " + ibsn);
        repository.deleteById(ibsn);
    }

    @Transactional
    public void updateBook(String ibsn, String newTitle, String newAuthor, String genre)
    {
        Book newBook = new Book(ibsn, newTitle, newAuthor, genre);
        repository.findById(newBook.getId())
                .ifPresentOrElse(s -> {
                    s.setAuthorName(newBook.getAuthorName()); s.setTitle(newBook.getTitle());
                }, () -> {
                    logger.info("ERROR: Try to update a non existent book: " + ibsn);
                    throw new ValidatorException("Could not find book based on ID.");
                });
        logger.info("Updating an existent book: " + ibsn);
    }

    public Book searchByIbsn(String ibsn){
        logger.info("Search a book by id: " + ibsn);
        return this.repository.findById(ibsn).get();
    }

    public Optional findOne(String  ISBN){
        logger.info("Search a book by id: " + ISBN);
        return repository.findById(ISBN);
    }

    public List<Book> getAllBooks() {
        logger.info("Retrieve all books: ");
        return repository.findAll();
    }

    public Set<Book> filterByGenre(String genre){
        List<Book> filteredBooks = getAllBooks();
        logger.info("Filtering by genre: " + genre);
        return filteredBooks.stream().filter(v->v.getGenre().equals(genre)).collect(Collectors.toSet());
    }

    public Iterable<Book> sortBooksByTitleAuthor() {
        Sort sort=new Sort("title").and(new Sort("authorName"));
        logger.info("Sorting books by title and author");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Book)s)
                .collect(Collectors.toList());
    }
}
