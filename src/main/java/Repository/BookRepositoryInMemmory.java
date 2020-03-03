package Repository;

import Model.BaseEntity;
import Model.Book;
import Model.Exceptions.ValidatorException;
import Model.Validators.IValidator;

import javax.swing.text.html.Option;
import java.util.*;
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
        Optional<ID> optionalID = Optional.of(id);
        return Optional.ofNullable(books.get(optionalID.orElseThrow(()-> new IllegalArgumentException("The ID must not be null!"))));
    }

    @Override
    public Iterable<T> findAll() {
        return new HashSet<>(books.values());
    }

    @Override
    public Optional<T> add(T entity) throws ValidatorException {
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(() -> new IllegalArgumentException("The entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(books.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<ID> optionalID = Optional.of(id);
        optionalID.orElseThrow(IllegalArgumentException::new);
        return Optional.ofNullable(books.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(()-> new IllegalArgumentException("The entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(books.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
