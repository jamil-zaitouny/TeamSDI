package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.BookValidator;
import Model.Validators.IValidator;
import Repository.RepositoryInMemory;
import Repository.RepositoryInterface;

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

    public void addBook(Book book) throws ValidatorException
    {
        validator.validate(book);
        this.repository.add(book);
    }

    public Set<Book>  getAllBooks()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
