package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.OrderDetailsEntity;
import com.ownsprojects.ecomerce.service.OrderDetailsService;
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
 * Controller for managing order details.
 */
@Slf4j
@RestController
@Api(value = "OrderDetailsController")
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    /**
     * Create new order details.
     *
     * @param orderDetails The Order details data to create.
     * @return The HTTP response with the order details created.
     */
    @ApiOperation(value = "Create new order details")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order details created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<OrderDetailsEntity> createOrderDetail(@Valid @RequestBody OrderDetailsEntity orderDetails) {
        OrderDetailsEntity createdOrderDetails = orderDetailsService.saveOrderDetail(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
    }

    /**
     * Get all the orders details.
     *
     * @return The HTTP response with the list of the orders details.
     */
    @ApiOperation(value = "Get all the order details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Orders details found"),
            @ApiResponse(code = 404, message = "Order details not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<OrderDetailsEntity>> getAllOrderDetails() {
        List<OrderDetailsEntity> ordersDetails = orderDetailsService.getAllOrderDetails();
        return ResponseEntity.status(ordersDetails.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(ordersDetails);
    }

    /**
     * Get order details by ID.
     *
     * @param id The ID of the order details.
     * @return The HTTP response with the order details or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get order details by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order details found"),
            @ApiResponse(code = 404, message = "Order details not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsEntity> getOrderDetails(@PathVariable("id") Long id) {
        Optional<OrderDetailsEntity> orderDetails = orderDetailsService.getOrderDatailById(id);
        return orderDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete order details by ID.
     *
     * @param id The ID or the order details to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete order details by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Order details not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetails(@PathVariable("id") Long id) {
        orderDetailsService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
