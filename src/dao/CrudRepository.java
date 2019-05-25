package dao;

import java.util.List;
import java.util.Optional;

// A ideia é organizar os métodos e padronizar a porra toda
public interface CrudRepository<T, K> {
    List<T> findAll();

    Optional<T> findById(K primaryKey);

    T save(T entity);

    T update(T entity, K id);

    T delete(K id);
}
