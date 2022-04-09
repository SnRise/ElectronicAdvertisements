package ru.itmo.electronic.advertisements.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.electronic.advertisements.model.Advertisement;
import ru.itmo.electronic.advertisements.model.Category;
import ru.itmo.electronic.advertisements.model.Region;
import ru.itmo.electronic.advertisements.model.User;
import ru.itmo.electronic.advertisements.repository.AdvertisementRepository;

/**
 * @author Madiyar Nurgazin
 */
@Service
@AllArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final RegionService regionService;

    @Transactional
    public Advertisement createAdvertisement(int categoryId, int regionId, double price, String description) {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new IllegalStateException("Login to create advertisement");
        }
        Category category = categoryService.findById(categoryId).orElseThrow(
                () -> new NoSuchElementException("Category with id=" + categoryId + " not found")
        );
        Region region = regionService.findById(regionId).orElseThrow(
                () -> new NoSuchElementException("Region with id=" + regionId + " not found")
        );

        Advertisement advertisement = new Advertisement();
        advertisement.setCategory(category);
        advertisement.setUser(user);
        advertisement.setRegion(region);
        advertisement.setPrice(price);
        advertisement.setDescription(description);
        return advertisementRepository.save(advertisement);
    }

    @Transactional
    public boolean deleteAdvertisement(int advertisementId) {
        User user = userService.getCurrentUser();
        Optional<Advertisement> advertisement = advertisementRepository.findById(advertisementId);

        if (advertisement.isPresent() && advertisement.get().getUser().equals(user)) {
            advertisementRepository.delete(advertisement.get());
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<Advertisement> getAllAdvertisements(int offset, int limit) {
        return advertisementRepository.findAll(offset, limit);
    }

    @Transactional(readOnly = true)
    public Optional<Advertisement> getAdvertisement(final int id) {
        return advertisementRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Advertisement> getAllAdvertisementsByUserId(int userId) {
        return advertisementRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Advertisement> getAllAdvertisementsByRegionId(int regionId) {
        return advertisementRepository.findAllByRegionId(regionId);
    }

    @Transactional(readOnly = true)
    public List<Advertisement> getAllAdvertisementsByCategoryId(int categoryId) {
        return advertisementRepository.findAllByCategoryId(categoryId);
    }
}
