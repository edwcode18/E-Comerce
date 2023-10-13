package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents an order detail entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity extends AuditableEntity {
    /**
     * Unique identifier for the order detail detail.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail", nullable = false)
    private Long idOrderDetail;

    /**
     * The quantity of the products.
     */
    @NotNull(message = "The quantity cannot be null")
    @Column(nullable = false)
    private Integer quantity;

    /**
     * The total price of the products;
     */
    @NotNull
    @Column(name = "total_price", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double totalPrice;

    /**
     * Represents the relationship with the order.
     */
    @ManyToOne
    @JoinColumn(name = "idOrder", insertable = false, updatable = false)
    private OrderEntity order;

    /**
     * Represents the relationship with the discount.
     */
    @ManyToOne
    @JoinColumn(name = "idDiscount", insertable = false, updatable = false)
    private DiscountEntity discount;

    /**
     * Represents the relationship with the product.
     */
    @OneToOne
    @JoinColumn(name = "idProduct", insertable = false, updatable = false)
    private ProductEntity product;
}
