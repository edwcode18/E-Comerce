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
 * Represent a discount entity in the system.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "discount")
public class DiscountEntity extends AuditableEntity {
    /**
     * Unique identifier for the discount.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount", nullable = false)
    private Long idDiscount;

    /**
     * The name of the discount.
     */
    @NotBlank(message = "The name cannot be blank")
    @Size(max = 50, message = "The name can only be 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * The description of the discount.
     */
    @NotBlank(message = "The description cannot be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * The code ticket of the discount.
     */
    @Size(max = 10, message = "The code ticket can only be 10 characters")
    @Column(name = "code_ticket", length = 10)
    private String codeTicket;

    /**
     * The percentage of the discount.
     */
    @NotNull(message = "The percentage discount cannot be null")
    @Column(name = "percentage_discount", nullable = false)
    private Integer percentageDiscount;

    /**
     * The start date of the discount.
     */
    @NotNull(message = "The start date cannot be null")
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    /**
     * The end date of the discount.
     */
    @NotNull(message = "The end date cannot be null")
    @Column(name = "end_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;
}
