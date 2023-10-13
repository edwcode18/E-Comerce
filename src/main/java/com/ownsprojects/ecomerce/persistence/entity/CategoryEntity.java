package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Represents a category entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "category")
public class CategoryEntity extends AuditableEntity {
    /**
     * Unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Long idCategory;

    /**
     * The name of the category.
     */
    @NotBlank(message = "The name cannot be blank and can only be 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The description of the category.
     */
    @NotBlank(message = "The description cannot be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
