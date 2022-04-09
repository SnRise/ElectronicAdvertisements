package ru.itmo.electronic.advertisements.query;

import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.Category;
import ru.itmo.electronic.advertisements.service.CategoryService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class CategoryQuery implements GraphQLQueryResolver {
    private final CategoryService categoryService;

    public List<Category> getCategories(int offset, int limit) {
        return categoryService.findAll(offset, limit);
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryService.findById(categoryId);
    }

    public Optional<Category> getCategory(String name) {
        return categoryService.findByName(name);
    }
}
