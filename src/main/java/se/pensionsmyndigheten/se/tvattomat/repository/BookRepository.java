package se.pensionsmyndigheten.se.tvattomat.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pensionsmyndigheten.se.tvattomat.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{
}
