package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing discounts.
 */
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

}
