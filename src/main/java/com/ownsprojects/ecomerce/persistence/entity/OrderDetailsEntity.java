package com.ownsprojects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail", nullable = false)
    private Long idOrderDetail;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "idOrder", insertable = false, updatable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "idDiscount", insertable = false, updatable = false)
    private DiscountEntity discount;

    @OneToOne
    @JoinColumn(name = "idProduct", insertable = false, updatable = false)
    private ProductEntity product;
}
