package com.ownsprojects.ecomerce.web.controller;


import com.ownsprojects.ecomerce.persistence.entity.CustomerEntity;
import com.ownsprojects.ecomerce.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequestMapping("/customers")
@Api(value = "CustomerController")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Create new customer.
     *
     * @param customer Customer data to create.
     * @return The HTTP response with the client created.
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create new customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<CustomerEntity> createCustomer(@Valid @RequestBody CustomerEntity customer) {
        CustomerEntity createdCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    /**
     * Get all the customers.
     *
     * @return The HTTP response with the list of the clients.
     */
    @GetMapping
    @ApiOperation(value = "Get all the customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customers found"),
            @ApiResponse(code = 404, message = "Customers not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customers = customerService.getAllCustomers();
        return ResponseEntity.status(customers.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(customers);
    }

    /**
     * Get customer by ID.
     *
     * @param id ID customer.
     * @return The HTTP response with the customer or NOT_FOUND if not found.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get customer by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable("id") Long id) {
        Optional<CustomerEntity> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete customer by ID.
     *
     * @param id ID customer to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete customer by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
