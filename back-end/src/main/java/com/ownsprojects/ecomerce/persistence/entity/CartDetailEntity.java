package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents a cart detail entity in the system.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "cart_detail")
public class CartDetailEntity extends AuditableEntity {
    /**
     * Unique identifier for the cart detail.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_detail", nullable = false)
    private Long idCartDetail;

    /**
     * The quantity of the products.
     */
    @NotNull(message = "The quantity cannot be null")
    @Column(nullable = false)
    private Integer quantity;

    /**
     * The subtotal of the products.
     */
    @NotNull(message = "The subtotal cannot be null")
    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double subtotal;

    /**
     * Represents the relationship with the shopping cart.
     */
    @ManyToOne
    @JoinColumn(name = "idCart", insertable = false, updatable = false)
    private ShoppingCartEntity shoppingCart;

    /**
     * Represent the relationship with the product.
     */
    @ManyToOne
    @JoinColumn(name = "idProduct", insertable = false, updatable = false)
    private ProductEntity product;
}
