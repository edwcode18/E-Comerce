package com.ownsprojects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_detail")
@Data
@NoArgsConstructor
public class CartDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_detail", nullable = false)
    private Long idCartDetail;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idCart", insertable = false, updatable = false)
    private ShoppingCartEntity shoppingCart;

    @ManyToOne
    @JoinColumn(name = "idProduct", insertable = false, updatable = false)
    private ProductEntity product;
}
