package com.ownsprojects.ecomerce.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ownsprojects.ecomerce.persistence.audit.AuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.security.crypto.bcrypt.BCrypt;

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
     * The email of the customer.
     */
    @Email(message = "The email is not valid")
    @NotBlank(message = "The email cannot be blank")
    @Size(max = 60, message = "The email can only be 60 characters")
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
    @Size(max = 100, message = "The password can only be 50 characters")
    @Column(length = 100)
    @Transient
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String salt;

    @Column(columnDefinition = "TEXT")
    @JsonIgnore
    private String passwordHash;

    /**
     * The shipping address of the customer.
     */
    @Size(max = 60, message = "The shipping address can only be 60 characters")
    @Column(name = "shiping_address", length = 60)
    private String shipingAddress;

    public void setPassword(String password) {
        // Genera una sal aleatoria
        this.salt = BCrypt.gensalt();
        // Combina la sal con la contraseña y almacena el hash
        this.passwordHash = BCrypt.hashpw(this.salt + password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        // Combina la sal almacenada con la contraseña proporcionada y verifica el hash
        String hashedPassword = BCrypt.hashpw(this.salt + password, this.salt);
        return hashedPassword.equals(this.passwordHash);
    }


}
