package com.ownsprojects.ecomerce.persistence.repository;

import com.ownsprojects.ecomerce.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to managing the roles.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
