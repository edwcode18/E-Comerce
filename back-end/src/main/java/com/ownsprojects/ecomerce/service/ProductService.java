package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.ProductEntity;
import com.ownsprojects.ecomerce.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing products.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Save a product.
     *
     * @param product The product to save.
     * @return The saved product.
     */
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    /**
     * Get a list of all products.
     *
     * @return A list of all products.
     */
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An Optional containing the product if found, or an empty Optional if not found.
     */
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
