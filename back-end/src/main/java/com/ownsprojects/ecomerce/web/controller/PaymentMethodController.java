package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.PaymentMethodEntity;
import com.ownsprojects.ecomerce.service.PaymentMethodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing payment methods.
 */
@Slf4j
@RestController
@Api(value = "PaymentMethodController")
@RequestMapping("/paymentMethods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    /**
     * Create new payment method.
     *
     * @param paymentMethod The Order
     * @return The HTTP response with the payment method created.
     */
    @ApiOperation(value = "Create new payment method")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment method created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<PaymentMethodEntity> createPaymentMethod(PaymentMethodEntity paymentMethod){
        PaymentMethodEntity createdPaymentMethod = paymentMethodService.savePaymentMethod(paymentMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentMethod);
    }

    /**
     * Get all the payment methods.
     *
     * @return The HTTP response with the list of the payment methods.
     */
    @ApiOperation(value = "Get all the payment methods")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment methods found"),
            @ApiResponse(code = 404, message = "Payment methods not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<PaymentMethodEntity>> getAllPaymentMethods(){
        List<PaymentMethodEntity> paymentMethods = paymentMethodService.getAllPaymentMethods();
        return ResponseEntity.status(paymentMethods.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(paymentMethods);
    }

    /**
     * Get payment method by ID.
     *
     * @param id The ID of the payment method.
     * @return The HTTP response with the payment method or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get payment method by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment method found"),
            @ApiResponse(code = 404, message = "Payment method not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodEntity> getPaymentMethod(@PathVariable("id") Long id){
        Optional<PaymentMethodEntity> paymentMethod = paymentMethodService.getPaymentMethodById(id);
        return paymentMethod.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete payment method by ID.
     *
     * @param id The ID or the payment method to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete payment method by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Payment method not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable("id") Long id){
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
