package Controller;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Repository.BookRepositoryInMemmory;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController {
    private BookRepositoryInMemmory<String, Book> repository;


    public BookController(BookRepositoryInMemmory<String, Book> repository) {
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
