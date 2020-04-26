package springjpa.Repository.DBRepository;

import springjpa.Model.Book;
import springjpa.Model.Exceptions.FileException;
import springjpa.Repository.SortRepository.Sort;
import springjpa.Repository.SortRepository.SortingRepository;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDBRepository implements SortingRepository<String, Book> {
    HashMap<String, Book> books;
    Connection connection;


    @Override
    public Optional<Book> findOne(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    private void loadBooks() throws FileException {
        try {
            String selectBooks="select * from books";
            PreparedStatement selectBooksStatement=connection.prepareStatement(selectBooks);
            ResultSet booksSet=selectBooksStatement.executeQuery();
            System.out.println(booksSet);
            while(booksSet.next()) {
                String isbn=booksSet.getString("isbn");
                String title=booksSet.getString("title");
                String authorName=booksSet.getString("authorName");
                String genre=booksSet.getString("genre");
                books.put(isbn,new Book(isbn,title, authorName,genre));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
    }

    public BookDBRepository() throws FileException {
        try {
            books=new HashMap<>();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/bookshop", "postgres", "admin");
        } catch (SQLException e) {
            throw new FileException("Could not connect to the database!");
        }
        try {
            loadBooks();
        } catch (FileException e) {
            throw e;
        }
    }

    @Override
    public Iterable<Book> findAll(Sort sort) {
        return sort.sort(books.values().stream()
                .map(book -> (Object) book)
                .collect(Collectors.toList()))
                .stream().map(book -> (Book) book)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Book> findAll() {
        Set<Book> allEntities = new HashSet<>();

        try {
            String selectBooks="select * from books";
            PreparedStatement selectBooksStatement=connection.prepareStatement(selectBooks);
            ResultSet booksSet=selectBooksStatement.executeQuery();
            System.out.println(booksSet);
            while(booksSet.next()) {
                String isbn=booksSet.getString("isbn");
                String title=booksSet.getString("title");
                String authorName=booksSet.getString("authorName");
                String genre=booksSet.getString("genre");
                allEntities.add(new Book(isbn,title, authorName,genre));
            }
        } catch (SQLException e) {
            throw new FileException("There was some problem with the database!");
        }
        return allEntities;
    }


    @Override
    public Optional<Book> add(Book entity) throws FileException {
        System.out.println(entity);
        Optional<Book> previous=Optional.ofNullable(books.putIfAbsent(entity.getId(), entity));
        previous.ifPresentOrElse(book -> {}, () -> {
            String addBooks = "insert into books (isbn, title, authorName, genre) values(?,?,?,?)";
            try {
                PreparedStatement addBookStatement = connection.prepareStatement(addBooks);
                addBookStatement.setString(1, entity.getId());
                addBookStatement.setString(2, entity.getTitle());
                addBookStatement.setString(3, entity.getAuthorName());
                addBookStatement.setString(4, entity.getGenre());
                addBookStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Book> delete(String isbn) {
        Optional<Book> previous=Optional.ofNullable(books.remove(isbn));
        previous.ifPresent(book -> {
            try {
                PreparedStatement deleteBookStatement=connection.prepareStatement("delete from books where isbn=?");
                deleteBookStatement.setString(1,isbn);
                deleteBookStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return previous;
    }

    @Override
    public Optional<Book> update(Book entity) throws FileException {
        Optional<Book> savedValue=Optional.ofNullable(books.computeIfPresent(entity.getId(), (k, v) -> entity));
        savedValue.ifPresent( client -> {
            try {
                PreparedStatement bookUpdateStatement=connection.prepareStatement("update books set title=?, authorName=?, genre=? where isbn=?");
                bookUpdateStatement.setString(1,entity.getTitle());
                bookUpdateStatement.setString(2,entity.getAuthorName());
                bookUpdateStatement.setString(3,entity.getGenre());
                bookUpdateStatement.setString(4,entity.getId());
                bookUpdateStatement.executeUpdate();
            } catch (SQLException e) {
                throw new FileException("There was some problem with the database!");
            }
        });
        return savedValue;
    }
}
