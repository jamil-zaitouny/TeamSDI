package Repository;

import Model.BaseEntity;
import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.IValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepositoryInMemmory<ID, T extends BaseEntity<ID>> implements RepositoryInterface<ID, T>
{
    private Map<ID, T> books;
    private IValidator<T> validator;

    public BookRepositoryInMemmory(IValidator<T> validator) {
        this.validator = validator;
        books = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return books.entrySet()
                .stream()
                .map(entry -> entry.getValue()).collect(Collectors.toSet());
    }

    @Override
    public Optional<T> add(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(books.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(books.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(books.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
