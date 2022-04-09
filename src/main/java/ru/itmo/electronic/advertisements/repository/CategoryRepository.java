package ru.itmo.electronic.advertisements.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.electronic.advertisements.model.Category;

/**
 * @author Madiyar Nurgazin
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value="SELECT * FROM categories c ORDER BY c.id DESC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<Category> findAll(int offset, int limit);

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}
