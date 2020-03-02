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
        Optional<ID> optionalId=Optional.of(id);
        return Optional.ofNullable(clients.get(optionalId.orElseThrow(()->new IllegalArgumentException("The ID must not be null!"))));
    }

    @Override
    public Iterable<T> findAll() {
        return clients.values()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<T> add(T entity) throws ValidatorException {
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(() -> new IllegalArgumentException("The entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(clients.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<ID> optionalID = Optional.of(id);
        optionalID.orElseThrow(IllegalArgumentException::new);
        return Optional.ofNullable(clients.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(() -> new IllegalArgumentException("The entity must not be null!"));
        validator.validate(entity);
        return Optional.ofNullable(clients.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
