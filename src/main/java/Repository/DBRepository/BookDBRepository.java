package Repository.DBRepository;

import Model.Book;
import Model.Exceptions.ValidatorException;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;

import java.io.IOException;
import java.util.Optional;

public class BookDBRepository implements SortingRepository<String, Book> {

    @Override
    public Iterable<Book> findAll(Sort sort) {
        return null;
    }

    @Override
    public Optional<Book> findOne(String s) {
        return Optional.empty();
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> add(Book entity) throws ValidatorException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<Book> delete(String s) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Book entity) throws ValidatorException {
        return Optional.empty();
    }
}
