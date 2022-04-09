package ru.itmo.electronic.advertisements.query;

import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Advertisement;
import ru.itmo.electronic.advertisements.service.AdvertisementService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@AllArgsConstructor
public class AdvertisementQuery implements GraphQLQueryResolver {
    private final AdvertisementService advertisementService;

    public List<Advertisement> getAdvertisements(int offset, int limit) {
        return advertisementService.getAllAdvertisements(offset, limit);
    }

    public Optional<Advertisement> getAdvertisement(int id) {
        return advertisementService.getAdvertisement(id);
    }

    public List<Advertisement> getAdvertisementsByRegionId(int regionId) {
        return advertisementService.getAllAdvertisementsByRegionId(regionId);
    }

    public List<Advertisement> getAdvertisementsByUserId(int userId) {
        return advertisementService.getAllAdvertisementsByUserId(userId);
    }

    public List<Advertisement> getAdvertisementsByCategoryId(int categoryId) {
        return advertisementService.getAllAdvertisementsByCategoryId(categoryId);
    }
}
