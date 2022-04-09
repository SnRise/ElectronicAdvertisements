package ru.itmo.electronic.advertisements.query;

import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Region;
import ru.itmo.electronic.advertisements.service.RegionService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class RegionQuery implements GraphQLQueryResolver {
    private final RegionService regionService;

    public List<Region> getRegions(int offset, int limit) {
        return regionService.findAll(offset, limit);
    }

    public Optional<Region> getRegion(int regionId) {
        return regionService.findById(regionId);
    }

    public Optional<Region> getRegion(String name) {
        return regionService.findByName(name);
    }
}
