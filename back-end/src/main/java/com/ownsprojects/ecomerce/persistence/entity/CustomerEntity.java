package com.ownsprojects.ecomerce.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Set;

/**
 * Represents a customer entity in the system.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class CustomerEntity extends AuditableEntity {
    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer", nullable = false)
    @JsonIgnore
    private Long idCustomer;

    /**
     * The first name of the customer.
     */
    @NotBlank(message = "The first name cannot be blank")
    @Size(max = 20, message = "The first name can only be 20 characters")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    /**
     * The last name of the customer.
     */
    @NotBlank(message = "The last name cannot be blank")
    @Size(max = 20, message = "The last name can only be 20 characters")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    /**
     * The name of the customer.
     */
    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30, unique = true, name = "user_name")
    private String userName;

    /**
     * The email of the customer.
     */
    @Email(message = "The email is not valid")
    @NotBlank(message = "The email cannot be blank")
    @Size(max = 80, message = "The email can only be 60 characters")
    @Column(nullable = false, length = 80, unique = true)
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
    @NotBlank(message = "The password cannot blank")
    private String password;

    /**
     * The shipping address of the customer.
     */
    @Size(max = 60, message = "The shipping address can only be 60 characters")
    @Column(name = "shiping_address", length = 60)
    private String shipingAddress;

    /**
     * Represents the relationship with roles entity.
     */
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    /**
     * Represents the roles as strings.
     */
    @Transient
    private Set<String> rolesUser;
}
