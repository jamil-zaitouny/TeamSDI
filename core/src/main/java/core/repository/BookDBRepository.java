package core.repository;


import core.model.Book;
import org.springframework.stereotype.Repository;

public interface BookDBRepository extends BaseEntityRepository<String, Book> {

}
