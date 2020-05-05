package core.service;

import core.model.Book;
import core.repository.BookDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import core.model.Exceptions.ValidatorException;
import core.model.Validators.IValidator;

import javax.enterprise.event.Observes;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.util.stream.Collectors;
@Service
public class BookService implements BookServiceInterface{
    final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDBRepository repository;

    @Autowired
    private IValidator<Book> validator;

    public void addBook(Book book) throws ValidatorException {
        validator.validate(book);
        Optional<Book> previous=repository.findById(book.getId());
        previous.ifPresent(s -> {
            logger.info("ERROR: Try to add an existent book: ");
            throw new ValidatorException("ID already exists.");
        });
        logger.info("Adding a new book: ");
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

    //@Transactional
    public void updateBook(Book newBook)
    {
        //System.out.println(newBook);
        /*repository.findById(newBook.getId())
                .ifPresentOrElse(s -> {
                    s.setAuthorName(newBook.getAuthorName()); s.setTitle(newBook.getTitle()); s.setGenre(newBook.getGenre());
                    System.out.println(s);
                }, () -> {
                    logger.info("ERROR: Try to update a non existent book: " );
                    throw new ValidatorException("Could not find book based on ID.");
                });*/
        this.deleteBook(newBook.getId());
        this.addBook(newBook);
        logger.info("Updating an existent book: ");
    }

    public Optional<Book> findOne(String  ISBN){
        logger.info("Search a book by id: " + ISBN);
        return repository.findById(ISBN);
    }

    public List<Book> getAllBooks() {
        logger.info("Retrieve all books: ");
        return repository.findAll();
    }

    public List<Book> filterByGenre(String genre){
        logger.info("Filtering by genre: " + genre);
        List<Book> filteredBooks = getAllBooks();
        return filteredBooks.stream().filter(v->v.getGenre().equals(genre)).collect(Collectors.toList());
    }

    /*public Iterable<Book> sortBooksByTitleAuthor() {
        Sort sort=new Sort("title").and(new Sort("authorName"));
        logger.info("Sorting books by title and author");
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Book)s)
                .collect(Collectors.toList());
    }*/
}
