package Repository;

import Model.BaseEntity;
import Model.Exceptions.ValidatorException;
import Model.Validators.IValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientRepositoryInMemory<ID, T extends BaseEntity<ID>> implements RepositoryInterface<ID, T>
{
    private Map<ID, T> clients;
    private IValidator<T> validator;

    public ClientRepositoryInMemory(IValidator<T> validator) {
        this.validator = validator;
        clients = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(clients.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return clients.values()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<T> add(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(clients.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(clients.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(clients.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
