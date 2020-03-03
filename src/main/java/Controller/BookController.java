package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Repository.RepositoryInMemory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController {
    private RepositoryInMemory<String, Book> repository;


    public BookController(RepositoryInMemory<String, Book> repository) {
        this.repository = repository;
    }

    public void addBook(Book book) throws ValidatorException
    {
        this.repository.add(book);
    }

    public Set<Book>  getAllBooks()
    {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
