package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.BookValidator;
import Model.Validators.IValidator;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController {
    private RepositoryInMemory<String, Book> repository;
    private IValidator<Book> validator;

    public BookController(RepositoryInMemory<String, Book> repository) {
        validator = new BookValidator();
        this.repository = repository;
    }

    public void addBook(String IBSN, String title, String author, String genre) throws ValidatorException {
        Book book = new Book(IBSN, title, author, genre);
        validator.validate(book);
        this.repository.add(book);
    }

    public void deleteBook(String ibsn) {
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

    public Set<Book> getAllBooks() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
