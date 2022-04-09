package ru.itmo.electronic.advertisements.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.electronic.advertisements.model.Region;
import ru.itmo.electronic.advertisements.repository.RegionRepository;

/**
 * @author Madiyar Nurgazin
 */
@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public Region createRegion(String name) {
        if (exists(name)) {
            throw new IllegalArgumentException("Region with name '" + name + "' already exists");
        }
        Region region = new Region();
        region.setName(name);

        regionRepository.save(region);
        return region;
    }

    public Optional<Region> findById(int regionId) {
        return regionRepository.findById(regionId);
    }

    public Optional<Region> findByName(String name) {
        return regionRepository.findByName(name);
    }

    public List<Region> findAll(int offset, int limit) {
        return regionRepository.findAll(offset, limit);
    }

    private boolean exists(String name) {
        return regionRepository.existsByName(name);
    }
}
