package springjpa.Repository.SortRepository;

import springjpa.Model.BaseEntity;
import springjpa.Repository.RepositoryInterface;

import java.io.Serializable;

public interface SortingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends RepositoryInterface<ID, T> {

    Iterable<T> findAll(Sort sort);
}