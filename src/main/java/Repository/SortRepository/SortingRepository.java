package Repository.SortRepository;

import Model.BaseEntity;
import Repository.RepositoryInterface;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Component
public interface SortingRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends RepositoryInterface<ID, T> {

    Iterable<T> findAll(Sort sort);
}