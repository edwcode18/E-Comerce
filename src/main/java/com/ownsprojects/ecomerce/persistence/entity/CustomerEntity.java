package com.ownsprojects.ecomerce.persistence.entity;

import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Represents a customer entity in the system.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity extends AuditableEntity {
    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", nullable = false)
    private Long idCustomer;

    /**
     * The first name of the customer.
     */
    @NotBlank(message = "The first name cannot be blank")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    /**
     * The last name of the customer.
     */
    @NotBlank(message = "The last name cannot be blank")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    /**
     * The email of the customer.
     */
    @Email(message = "The email is not valid")
    @NotBlank(message = "The email cannot be blank")
    @Column(nullable = false, length = 60, unique = true)
    private String email;

    /**
     * The phone number of the customer.
     */
    @Size(max = 15, message = "The phone number can only be 15 numbers")
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    /**
     * The password of the customer.
     */
    @Size(max = 15, message = "The password can only be 50 characters")
    @NotBlank(message = "The password cannot be blank")
    @Column(length = 50)
    private String password;

    /**
     * The shipping address of the customer.
     */
    @Size(max = 15, message = "The shipping address can only be 60 characters")
    @Column(name = "shiping_address", length = 60)
    private String shipingAddress;
}
