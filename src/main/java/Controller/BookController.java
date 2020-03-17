package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Model.Validators.BookValidator;
import Model.Validators.IValidator;
import Repository.RepositoryInMemory;
import Repository.RepositoryInterface;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController {
    private RepositoryInterface<String, Book> repository;
    private IValidator<Book> validator;

    public BookController(RepositoryInterface<String, Book> repository) {
        validator = new BookValidator();
        this.repository = repository;
    }

    public void addBook(String IBSN, String title, String author, String genre) throws ValidatorException, IOException {
        Book book = new Book(IBSN, title, author, genre);
        validator.validate(book);
        this.repository.add(book);
    }

    public void deleteBook(String ibsn)
    {
        this.repository.delete(ibsn);
    }

    public void updateBook(String ibsn, String newTitle, String newAuthor, String genre)
    {
        Book newBook = new Book(ibsn, newTitle, newAuthor, genre);
        this.validator.validate(newBook);
        this.repository.update(newBook);
    }

    public Book searchByIbsn(String ibsn){
        return this.repository.findOne(ibsn).get();
    }

    public Optional findOne(String  ISBN){
        return repository.findOne(ISBN);
    }

    public Set<Book> getAllBooks() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Book> filterByGenre(String genre){
        Set<Book> filteredBooks = getAllBooks();
        return filteredBooks.stream().filter(v->v.getGenre().equals(genre)).collect(Collectors.toSet());
    }
}
