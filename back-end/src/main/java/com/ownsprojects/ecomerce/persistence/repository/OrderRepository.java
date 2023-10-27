package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing orders.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
