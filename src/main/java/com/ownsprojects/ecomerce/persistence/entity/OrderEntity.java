package com.ownsprojects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Long idOrder;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime date;

    @Column(length = 15, nullable = false)
    private String status;

    @Column(name = "additional_notes", columnDefinition = "TEXT")
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "idCustomer", insertable = false, updatable = false)
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "idPaymentMethod", insertable = false, updatable = false)
    private PaymentMethodEntity paymentMethod;
}
