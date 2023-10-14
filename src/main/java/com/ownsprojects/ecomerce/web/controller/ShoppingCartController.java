package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.ShoppingCartEntity;
import com.ownsprojects.ecomerce.service.ShoppingCartService;
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
 * Controller for managing shopping carts.
 */
@Slf4j
@RestController
@Api(value = "ShoppingCartController")
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Create new shopping cart.
     *
     * @param shoppingCart The Shopping cart to create.
     * @return The HTTP response with the shopping cart created.
     */
    @ApiOperation(value = "Create new shopping cart")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Shopping cart created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<ShoppingCartEntity> createShoppingCart(@Valid @RequestBody ShoppingCartEntity shoppingCart) {
        ShoppingCartEntity createdShoppingCart = shoppingCartService.saveShoppingCart(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingCart);
    }

    /**
     * Get all the shopping carts.
     *
     * @return The HTTP response with the list of the shopping carts.
     */
    @ApiOperation(value = "Get all the shopping carts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Shopping carts found"),
            @ApiResponse(code = 404, message = "Shopping carts not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<ShoppingCartEntity>> getAllShoppingCarts() {
        List<ShoppingCartEntity> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        return ResponseEntity.status(shoppingCarts.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(shoppingCarts);
    }

    /**
     * Get shopping cart by ID.
     *
     * @param id The ID of the shopping cart.
     * @return The HTTP response with the shopping cart or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get shopping cart by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Shopping cart found"),
            @ApiResponse(code = 404, message = "Shopping cart not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartEntity> getShoppingCart(@PathVariable("id") Long id) {
        Optional<ShoppingCartEntity> shoppingCart = shoppingCartService.getShoppingCartById(id);
        return shoppingCart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete shopping cart by ID.
     *
     * @param id The ID or the shopping cart to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete shopping cart by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Shopping cart not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable("id") Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.noContent().build();
    }
}
