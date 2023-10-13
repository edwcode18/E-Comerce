package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.PaymentMethodEntity;
import com.ownsprojects.ecomerce.persistence.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to managing payment methods.
 */
@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Save a payment method.
     *
     * @param paymentMethod The payment method to save.
     * @return The saved payment method.
     */
    public PaymentMethodEntity savePaymentMethod(PaymentMethodEntity paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    /**
     * Get a list of all payment methods.
     *
     * @return A list of all payment methods.
     */
    public List<PaymentMethodEntity> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    /**
     * Get a payment method by its ID.
     *
     * @param id The ID of the payment method to retrieve.
     * @return An Optional containing the payment method if found, or an empty Optional if not found.
     */
    public Optional<PaymentMethodEntity> getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id);
    }

    /**
     * Delete a payment method by its ID.
     *
     * @param id The ID of the payment method to delete.
     */
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
