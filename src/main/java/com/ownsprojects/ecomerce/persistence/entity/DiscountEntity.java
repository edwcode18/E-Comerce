package com.ownsprojects.ecomerce.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount", nullable = false)
    private Long idDiscount;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "code_ticket", length = 10)
    private String codeTicket;

    @Column(name = "percentage_discount", nullable = false)
    private Integer percentageDiscount;

    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;
}
