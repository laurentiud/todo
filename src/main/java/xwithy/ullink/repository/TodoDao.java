package xwithy.ullink.repository;

import org.springframework.data.repository.CrudRepository;
import xwithy.ullink.repository.Todo;

import java.util.List;

public interface TodoDao extends CrudRepository<Todo, Long> {
    
    List<Todo> findAllByHash(String hash);
}