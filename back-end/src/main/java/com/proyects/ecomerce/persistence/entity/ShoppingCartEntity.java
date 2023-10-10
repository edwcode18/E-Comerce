package com.proyects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
@Data
@NoArgsConstructor
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart", nullable = false)
    private Long idCart;

    @Column(nullable = false, length = 15)
    private String status;

    @ManyToOne
    @JoinColumn(name = "idCustomer", insertable = false, updatable = false)
    private CustomerEntity customer;
}
