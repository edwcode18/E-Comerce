package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.DiscountEntity;
import com.ownsprojects.ecomerce.persistence.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing discounts.
 */
@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * Save a discount.
     *
     * @param discount The discount to save.
     * @return The saved discount.
     */
    public DiscountEntity saveDiscount(DiscountEntity discount) {
        return discountRepository.save(discount);
    }

    /**
     * Get a list of all discounts.
     *
     * @return A list of all discounts.
     */
    public List<DiscountEntity> getAllDiscounts() {
        return discountRepository.findAll();
    }

    /**
     * Get a discount by its ID.
     *
     * @param id The ID of the discount to retrieve.
     * @return An Optional containing the discount if found, or an empty Optional if not found.
     */
    public DiscountEntity getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    /**
     * Delete a discount by its ID.
     *
     * @param id The ID of the discount to delete.
     */
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
