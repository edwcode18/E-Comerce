package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.CategoryEntity;
import com.ownsprojects.ecomerce.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing categories.
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Save a category.
     *
     * @param category The category to save.
     * @return The saved category.
     */
    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    /**
     * Get a list of all categories.
     *
     * @return A list of all categories.
     */
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return An Optional containing the category if found, or an empty Optional if not found.
     */
    public Optional<CategoryEntity> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    /**
     * Delete a category by its ID.
     *
     * @param id The ID of the category to delete.
     */
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
