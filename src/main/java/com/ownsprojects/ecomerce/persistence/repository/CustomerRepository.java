package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing customers.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Get customer by name.
     * @param name The name of the customer.
     * @return The customer found.
     */
    Optional<CustomerEntity> findByUserName(String name);
}
