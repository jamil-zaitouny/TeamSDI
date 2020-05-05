package bookshop.core.repository;

import bookshop.core.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseEntityRepository<ID extends Serializable,
        T extends BaseEntity<ID>>
        extends JpaRepository<T, ID> {

}