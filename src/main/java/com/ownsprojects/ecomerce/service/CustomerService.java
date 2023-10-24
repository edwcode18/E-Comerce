package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.CustomerEntity;
import com.ownsprojects.ecomerce.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing customers.
 */
@Service
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Save a customer.
     *
     * @param customer The customer to save.
     * @return The saved customer.
     */
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        customer.setPassword(customer.getPassword());
        return customerRepository.save(customer);
    }

    /**
     * Get a list of all customers.
     *
     * @return A list of all customers.
     */
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Get a customer by its ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return An Optional containing the customer if found, or an empty Optional if not found.
     */
    public Optional<CustomerEntity> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Delete a customer by its ID.
     *
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        CustomerEntity customer = customerRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("The customer: " + name + " doesn't exist."));

        Collection<? extends GrantedAuthority> authorities = customer.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(customer.getEmail(),
                customer.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
