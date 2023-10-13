package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.OrderEntity;
import com.ownsprojects.ecomerce.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing order services.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Save a order;
     *
     * @param order The order to save.
     * @return The saved order.
     */
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    /**
     * Get a list of all orders.
     *
     * @return A list  of all orders.
     */
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Get order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return An Optional containing the order if found, or an empty Optional if not found.
     */
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Delete a order by its ID.
     *
     * @param id The ID of the order to delete.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
