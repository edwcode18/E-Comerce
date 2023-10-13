package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing shopping carts.
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
}
