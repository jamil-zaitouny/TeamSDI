package Repository.SortRepository;

import Model.BaseEntity;
import Repository.RepositoryInterface;

import java.io.Serializable;

public interface SortingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends RepositoryInterface<ID, T> {

    Iterable<T> findAll(Sort sort);
}