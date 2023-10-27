package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represents a shopping cart entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "shopping_cart")
public class ShoppingCartEntity extends AuditableEntity {
    /**
     * Unique identifier for the shopping cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart", nullable = false)
    private Long idCart;

    /**
     * The status of the shopping cart.
     */
    @NotBlank(message = "The status cannot be blank")
    @Size(max = 15, message = "The status can only be 15 characters")
    @Column(nullable = false, length = 15)
    private String status;

    /**
     * Represents the relationship with the customer.
     */
    @ManyToOne
    @JoinColumn(name = "idCustomer", insertable = false, updatable = false)
    private CustomerEntity customer;
}
