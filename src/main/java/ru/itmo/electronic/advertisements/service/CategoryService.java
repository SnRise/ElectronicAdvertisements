package ru.itmo.electronic.advertisements.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.electronic.advertisements.model.Category;
import ru.itmo.electronic.advertisements.repository.CategoryRepository;

/**
 * @author Madiyar Nurgazin
 */
@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name) {
        if (exists(name)) {
            throw new IllegalArgumentException("Category with name '" + name + "' already exists");
        }
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);

        return category;
    }

    public Optional<Category> findById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> findAll(int offset, int limit) {
        return categoryRepository.findAll(offset, limit);
    }

    private boolean exists(String name) {
        return categoryRepository.existsByName(name);
    }
}
