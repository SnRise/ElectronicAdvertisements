package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Category;
import ru.itmo.electronic.advertisements.service.CategoryService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class CategoryMutation implements GraphQLMutationResolver {
    private final CategoryService categoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Category createCategory(String name) {
        return categoryService.createCategory(name);
    }
}
