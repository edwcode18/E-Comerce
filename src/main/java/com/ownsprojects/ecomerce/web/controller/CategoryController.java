package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.CategoryEntity;
import com.ownsprojects.ecomerce.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing categories.
 */
@Slf4j
@RestController
@Api(value = "CategoryController")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Create new category.
     *
     * @param category The category data to create.
     * @return The HTTP response with the category created.
     */
    @ApiOperation(value = "Create new category")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<CategoryEntity> createCategory(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity createdCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    /**
     * Get all the categories.
     *
     * @return The HTTP response with the list of the categories.
     */
    @ApiOperation(value = "Get all the categories")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Categories found"),
            @ApiResponse(code = 404, message = "Categories not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        return ResponseEntity.status(categories.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(categories);
    }

    /**
     * Get category by ID.
     *
     * @param id The ID of the category.
     * @return The HTTP response with the category or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get category by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Category found"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable("id") Long id) {
        Optional<CategoryEntity> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete category by ID.
     *
     * @param id The ID or the category to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete category by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Category not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
