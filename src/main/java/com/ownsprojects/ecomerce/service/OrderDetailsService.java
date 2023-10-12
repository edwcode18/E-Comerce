package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.OrderDetailsEntity;
import com.ownsprojects.ecomerce.persistence.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public OrderDetailsEntity saveOrderDetail(OrderDetailsEntity orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetailsEntity> getAllOrderDetails(Long id) {
        return orderDetailsRepository.findAll();
    }

    public OrderDetailsEntity getOrderDatailById(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public void deleteOrderDetail(Long id) {
        orderDetailsRepository.deleteById(id);
    }
}
