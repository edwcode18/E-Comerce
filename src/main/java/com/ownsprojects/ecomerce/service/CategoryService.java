package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.CategoryEntity;
import com.ownsprojects.ecomerce.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity saveCategory(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
