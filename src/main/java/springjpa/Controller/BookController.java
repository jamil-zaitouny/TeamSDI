package springjpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springjpa.Model.Book;
import springjpa.Model.Exceptions.ValidatorException;
import springjpa.Model.Validators.IValidator;
import springjpa.Repository.DBRepository.BookDBRepository;
import springjpa.Repository.SortRepository.Sort;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookController {
    @Autowired
    private BookDBRepository repository;

    @Autowired
    private IValidator<Book> validator;

    /*public BookController(SortingRepository<String, Book> repository) {
        validator = new BookValidator();
        this.repository = repository;
    }*/

    public void addBook(String IBSN, String title, String author, String genre) throws ValidatorException, IOException {
        Book book = new Book(IBSN, title, author, genre);
        validator.validate(book);
        Optional<Book> previous=repository.findById(book.getId());
        previous.ifPresent(s -> {
            throw new ValidatorException("ID already exists.");
        });
        repository.save(book);
    }

    public void deleteBook(String ibsn)
    {
        Optional<Book> previous=repository.findById(ibsn);
        previous.orElseThrow(() -> {
            throw new ValidatorException("Could not find book based on ID.");
        });
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
                    throw new ValidatorException("Could not find book based on ID.");
                });
    }

    public Book searchByIbsn(String ibsn){
        return this.repository.findById(ibsn).get();
    }

    public Optional findOne(String  ISBN){
        return repository.findById(ISBN);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Set<Book> filterByGenre(String genre){
        List<Book> filteredBooks = getAllBooks();
        return filteredBooks.stream().filter(v->v.getGenre().equals(genre)).collect(Collectors.toSet());
    }

    public Iterable<Book> sortBooksByTitleAuthor() {
        Sort sort=new Sort("title").and(new Sort("authorName"));
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Book)s)
                .collect(Collectors.toList());
    }
}
