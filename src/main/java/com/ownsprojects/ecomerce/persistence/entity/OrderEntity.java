package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Represents an order entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ecomerce_order")
public class OrderEntity extends AuditableEntity {
    /**
     * Unique identifier for the order in the system.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    /**
     * The date of the order.
     */
    @NotNull(message = "The date cannot be null")
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime date;

    /**
     * The status of the order.
     */
    @NotBlank(message = "The status cannot be blank")
    @Size(max = 15, message = "The status can only be 15 characters")
    @Column(length = 15, nullable = false)
    private String status;

    /**
     * The additional notes of the order.
     */
    @Column(name = "additional_notes", columnDefinition = "TEXT")
    private String additionalNotes;

    /**
     * Represents the relationship with the customer.
     */
    @ManyToOne
    @JoinColumn(name = "idCustomer", insertable = false, updatable = false)
    private CustomerEntity customer;

    /**
     * Represents the relationship with the payment method.
     */
    @OneToOne
    @JoinColumn(name = "idPaymentMethod", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;
}
