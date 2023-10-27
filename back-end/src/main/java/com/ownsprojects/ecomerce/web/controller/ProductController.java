package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.ProductEntity;
import com.ownsprojects.ecomerce.service.ProductService;
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
 * Controller for managing products.
 */
@Slf4j
@RestController
@Api(value = "ProductController")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create new product.
     *
     * @param product The Product to create.
     * @return The HTTP response with the product created.
     */
    @ApiOperation(value = "Create new product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductEntity product) {
        ProductEntity createdProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Get all the products.
     *
     * @return The HTTP response with the list of the products.
     */
    @ApiOperation(value = "Get all the products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Products found"),
            @ApiResponse(code = 404, message = "Products not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ResponseEntity.status(products.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(products);
    }

    /**
     * Get product by ID.
     *
     * @param id The ID of the product.
     * @return The HTTP response with the product or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get product by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete product by ID.
     *
     * @param id The ID or the product to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete product by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
