package ru.itmo.electronic.advertisements.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.electronic.advertisements.model.Advertisement;

/**
 * @author Madiyar Nurgazin
 */
@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    @Query(value="SELECT * FROM advertisements a ORDER BY a.advertisement_id DESC OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<Advertisement> findAll(int offset, int limit);

    List<Advertisement> findAllByUserId(int userId);

    List<Advertisement> findAllByRegionId(int regionId);

    List<Advertisement> findAllByCategoryId(int categoryId);
}
