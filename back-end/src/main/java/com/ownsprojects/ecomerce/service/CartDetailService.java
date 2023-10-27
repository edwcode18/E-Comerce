package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.CartDetailEntity;
import com.ownsprojects.ecomerce.persistence.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing cart details.
 */
@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    @Autowired
    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    /**
     * Save a cart detail.
     *
     * @param cartDetail The cart detail to save.
     * @return The saved cart detail.
     */
    public CartDetailEntity saveCartDetail(CartDetailEntity cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    /**
     * Get a list of all cart details.
     *
     * @return A list of all cart details.
     */
    public List<CartDetailEntity> getAllCartDetails() {
        return cartDetailRepository.findAll();
    }

    /**
     * Get a cart detail by its ID.
     *
     * @param id The ID of the cart detail to retrieve.
     * @return An Optional containing the cart detail if found, or an empty Optional if not found.
     */
    public Optional<CartDetailEntity> getCartDetailById(Long id) {
        return cartDetailRepository.findById(id);
    }

    /**
     * Delete a cart detail by its ID.
     *
     * @param id The ID of the cart detail to delete.
     */
    public void deleteCartDetail(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
