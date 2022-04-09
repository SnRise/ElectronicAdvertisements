package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Region;
import ru.itmo.electronic.advertisements.service.RegionService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class RegionMutation implements GraphQLMutationResolver {
    private final RegionService regionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Region createRegion(String name) {
        return regionService.createRegion(name);
    }
}
