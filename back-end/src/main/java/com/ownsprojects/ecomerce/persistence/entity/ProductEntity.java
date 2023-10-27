package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents a product entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "product")
public class ProductEntity extends AuditableEntity {
    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    /**
     * The name of the product.
     */
    @NotBlank(message = "The name cannot be blank")
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The description of the product.
     */
    @NotBlank(message = "The description cannot be blank")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    /**
     * The price of the product.
     */
    @NotNull(message = "The price cannot be null")
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double price;

    /**
     * The available stock of the product.
     */
    @NotNull(message = "The available stock cannot be null")
    @Column(name = "available_stock", nullable = false)
    private Integer availableStock;

    /**
     * The size of the product.
     */
    @Size(max = 5, message = "The size can only be 5 characters")
    @Column(length = 5)
    private String size;

    /**
     * The color of the product.
     */
    @Size(max = 15, message = "The color can only be 15 characters")
    @Column(length = 15)
    private String color;

    /**
     * The gender of the product.
     */
    @Size(max = 1, message = "The gender can only be 1 character")
    @Column(length = 1)
    private String gender;

    /**
     * The image url of the product.
     */
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    /**
     * Represents the relationship with the discount.
     */
    @ManyToOne
    @JoinColumn(name = "idDiscount", insertable = false, updatable = false)
    private DiscountEntity discount;

    /**
     * Represents the relationship with the category.
     */
    @OneToOne
    @JoinColumn(name = "idCategory", insertable = false, updatable = false)
    private CategoryEntity category;
}
