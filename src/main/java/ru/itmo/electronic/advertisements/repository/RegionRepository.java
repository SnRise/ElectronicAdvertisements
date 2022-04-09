package ru.itmo.electronic.advertisements.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.electronic.advertisements.model.Region;

/**
 * @author Madiyar Nurgazin
 */
public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query(value="SELECT * FROM regions r ORDER BY r.id DESC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<Region> findAll(int offset, int limit);

    Optional<Region> findByName(String name);

    boolean existsByName(String name);
}
