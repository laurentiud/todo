package xwithy.ullink.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TodoDao extends CrudRepository<Todo, Long> {
    List<Todo> findAllByHash(String hash);
}