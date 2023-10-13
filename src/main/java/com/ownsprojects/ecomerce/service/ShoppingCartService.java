package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.ShoppingCartEntity;
import com.ownsprojects.ecomerce.persistence.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing the shopping carts.
 */
@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Save a shopping cart.
     *
     * @param shoppingCart The shopping cart to save.
     * @return The saved shopping cart.
     */
    public ShoppingCartEntity saveShoppingCart(ShoppingCartEntity shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    /**
     * Get a list of all shopping carts.
     *
     * @return A list of all shopping carts.
     */
    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    /**
     * Get a shopping cart by its ID.
     *
     * @param id The ID of the shopping cart to retrieve.
     * @return An Optional containing the shopping cart if found, or an empty Optional if not found.
     */
    public Optional<ShoppingCartEntity> getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    /**
     * Delete a shopping cart by its ID.
     *
     * @param id The ID of the shopping cart to delete.
     */
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
