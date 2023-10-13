package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Represents a payment method entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "payment_method")
public class PaymentMethodEntity extends AuditableEntity {

    /**
     * Unique identifier for the payment method.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_method", nullable = false)
    private Long idPaymentMethod;

    /**
     * The name of the payment method.
     */
    @NotBlank(message = "The name cannot be blank and can only be 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The description of the payment method.
     */
    @NotBlank(message = "The description cannot be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
