package Repository.DBRepository;

import Model.Book;
import Model.Exceptions.FileException;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookDBRepository implements SortingRepository<String, Book> {
    HashMap<String, Book> books;

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Book> findOne(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    public void loadBooks() throws FileException {
        String selectBooks = "select * from books";
        books = new HashMap<>();
        jdbcOperations.query(selectBooks, (rs, rowNum) -> {
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String authorName = rs.getString("authorName");
            String genre = rs.getString("genre");
            Book book =  new  Book(isbn, title, authorName, genre);
            books.put(isbn, book);
            return book;
            }
        );
    }

    public BookDBRepository() throws FileException {
    }

    @Override
    public Iterable<Book> findAll(Sort sort) {
        /*return sort.sort(books.values().stream()
                .map(book -> (Object) book)
                .collect(Collectors.toList()))
                .stream().map(book -> (Book) book)
                .collect(Collectors.toList());
         */
        return sort.sort(StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList()))
                .stream().map(book-> (Book) book)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Book> findAll() {
        String selectBooks = "select * from books";
        return new HashSet<>(jdbcOperations.query(selectBooks, (rs, rowNum) -> {
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String authorName = rs.getString("authorName");
            String genre = rs.getString("genre");

            return new Book(isbn, title, authorName, genre);
        }));
    }


    @Override
    public Optional<Book> add(Book entity) throws FileException {
        System.out.println(entity);
        Optional<Book> previous = Optional.ofNullable(books.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(book -> {
        }, () -> {
            String sql = "insert into books (isbn, title, authorName, genre) values(?,?,?,?)";
            jdbcOperations.update(sql, entity.getId(), entity.getTitle(), entity.getAuthorName(), entity.getGenre());
        });
        return previous;
    }

    @Override
    public Optional<Book> delete(String isbn) {
        Optional<Book> previous = Optional.ofNullable(books.remove(isbn));
        System.out.println(previous);
        previous.ifPresent(book -> {
            String sql = "delete from books where isbn=?";
            jdbcOperations.update(sql, isbn);
        });
        return previous;
    }

    @Override
    public Optional<Book> update(Book entity) throws FileException {
        Optional<Book> savedValue = Optional.ofNullable(books.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent(book -> {
            String sql = "update books set title=?, authorName=?, genre=? where isbn=?";
            jdbcOperations.update(sql, entity.getTitle(), entity.getAuthorName(), entity.getGenre(),
                    entity.getId());
        });
        return savedValue;
    }
}
