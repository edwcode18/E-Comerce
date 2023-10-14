package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.DiscountEntity;
import com.ownsprojects.ecomerce.service.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing customers.
 */
@Slf4j
@RestController
@Api(value = "DiscountController")
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Create new discount.
     *
     * @param discount The Discount data to create.
     * @return The HTTP response with the discount created.
     */
    @ApiOperation(value = "Create new discount")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Discount created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<DiscountEntity> createDiscount(@Valid @RequestBody DiscountEntity discount) {
        DiscountEntity createdDiscount = discountService.saveDiscount(discount);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscount);
    }

    /**
     * Get all the discounts.
     *
     * @return The HTTP response with the list of the discounts.
     */
    @ApiOperation(value = "Get all the discounts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Discounts found"),
            @ApiResponse(code = 404, message = "Discounts not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<DiscountEntity>> getAllDiscounts() {
        List<DiscountEntity> discounts = discountService.getAllDiscounts();
        return ResponseEntity.status(discounts.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(discounts);
    }

    /**
     * Get discount by ID.
     *
     * @param id The ID of the discount.
     * @return The HTTP response with the discount or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get discount by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Discount found"),
            @ApiResponse(code = 404, message = "Discount not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DiscountEntity> getDiscount(@PathVariable("id") Long id) {
        Optional<DiscountEntity> discount = discountService.getDiscountById(id);
        return discount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete discount by ID.
     *
     * @param id The ID or the discount to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete discount by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Discount not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiscount(@PathVariable("id") Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }
}