package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.OrderDetailsEntity;
import com.ownsprojects.ecomerce.persistence.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to managing order details.
 */
@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    /**
     * Save a order detail.
     *
     * @param orderDetails The order detail to save.
     * @return The saved order detail.
     */
    public OrderDetailsEntity saveOrderDetail(OrderDetailsEntity orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    /**
     * Get a list of all order details.
     *
     * @return A list of all order details.
     */
    public List<OrderDetailsEntity> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    /**
     * Get a order detail by its ID.
     *
     * @param id The ID of the order detail to retrieve.
     * @return An Optional containing the order detail if found, or an empty Optional if not found.
     */
    public OrderDetailsEntity getOrderDatailById(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    /**
     * Delete a order detail by its ID.
     *
     * @param id The ID of the order detail to delete.
     */
    public void deleteOrderDetail(Long id) {
        orderDetailsRepository.deleteById(id);
    }
}
