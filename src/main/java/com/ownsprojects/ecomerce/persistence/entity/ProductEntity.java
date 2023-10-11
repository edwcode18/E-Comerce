package com.ownsprojects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "NUMERIC(10,2)")
    private Double price;

    @Column(name = "available_stock", nullable = false)
    private Integer availableStock;

    @Column(length = 5)
    private String size;

    @Column(length = 15)
    private String color;

    @Column(length = 1)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "idDiscount", insertable = false, updatable = false)
    private DiscountEntity discount;

    @OneToOne
    @JoinColumn(name = "idCategory", insertable = false, updatable = false)
    private CategoryEntity category;
}
