package Repository;

import Model.BaseEntity;
import Model.Exceptions.ValidatorException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Optional;

public interface RepositoryInterface<ID, T extends BaseEntity<ID>>
{
    /**
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> findOne(ID id);

    /**
     *
     * @return all entities.
     */
    Iterable<T> findAll();

    /**
     * Adds the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was added otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> add(T entity) throws ValidatorException, IOException;

    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> delete(ID id) ;

    /**
     * Updates the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> update(T entity) throws ValidatorException;
}
