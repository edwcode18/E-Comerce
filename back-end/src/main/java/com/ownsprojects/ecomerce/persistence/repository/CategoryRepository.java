package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing categories.
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
