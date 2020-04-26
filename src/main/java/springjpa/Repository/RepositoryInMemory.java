package springjpa.Repository;

import springjpa.Model.BaseEntity;
import springjpa.Model.Exceptions.ValidatorException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryInMemory<ID, T extends BaseEntity<ID>> implements RepositoryInterface<ID, T>
{
    private Map<ID, T> entities;

    public RepositoryInMemory() {
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return entities.values()
                .stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<T> add(T entity) throws ValidatorException, IOException{
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(() -> new IllegalArgumentException("The entity must not be null!"));
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id)  {
        Optional<ID> optionalID = Optional.of(id);
        optionalID.orElseThrow(IllegalArgumentException::new);
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> optionalT = Optional.ofNullable(entity);
        optionalT.orElseThrow(() -> new IllegalArgumentException("The entity must not be null!"));
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
