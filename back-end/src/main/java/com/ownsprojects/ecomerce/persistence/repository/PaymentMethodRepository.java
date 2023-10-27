package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing payment methods.
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {
}
