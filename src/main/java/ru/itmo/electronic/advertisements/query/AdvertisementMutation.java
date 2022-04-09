package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Advertisement;
import ru.itmo.electronic.advertisements.service.AdvertisementService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@AllArgsConstructor
public class AdvertisementMutation implements GraphQLMutationResolver {
    private final AdvertisementService advertisementService;

    @PreAuthorize("isAuthenticated()")
    public Advertisement createAdvertisement(int categoryId, int regionId, double price, String description) {
        return advertisementService.createAdvertisement(categoryId, regionId, price, description);
    }

    @PreAuthorize("isAuthenticated()")
    public boolean deleteAdvertisement(int advertisementId) {
        return advertisementService.deleteAdvertisement(advertisementId);
    }
}
