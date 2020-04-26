package springjpa.Controller;

import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Model.Validators.BookValidator;
import springjpa.Model.Validators.IValidator;
import springjpa.Repository.SortRepository.Sort;
import springjpa.Repository.SortRepository.SortingRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookController {
    private SortingRepository<String, Book> repository;
    private IValidator<Book> validator;

    public BookController(SortingRepository<String, Book> repository) {
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
        return StreamSupport.stream(sortBooksByTitleAuthor().spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Book> filterByGenre(String genre){
        Set<Book> filteredBooks = getAllBooks();
        return filteredBooks.stream().filter(v->v.getGenre().equals(genre)).collect(Collectors.toSet());
    }

    public Iterable<Book> sortBooksByTitleAuthor() {
        Sort sort=new Sort(Sort.Direction.DESC,"title");
        return repository.findAll(sort);
    }
}
