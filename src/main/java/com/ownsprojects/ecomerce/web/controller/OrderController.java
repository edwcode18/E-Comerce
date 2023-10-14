package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.OrderEntity;
import com.ownsprojects.ecomerce.service.OrderService;
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
 * Controller for managing orders.
 */
@Slf4j
@RestController
@Api(value = "OrderController")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create new order.
     *
     * @param order The Order to create.
     * @return The HTTP response with the order created.
     */
    @ApiOperation(value = "Create new order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@Valid @RequestBody OrderEntity order) {
        OrderEntity createdOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     * Get all the orders.
     *
     * @return The HTTP response with the list of the orders.
     */
    @ApiOperation(value = "Get all the orders")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Orders found"),
            @ApiResponse(code = 404, message = "Orders not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> orders = orderService.getAllOrders();
        return ResponseEntity.status(orders.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(orders);
    }

    /**
     * Get order by ID.
     *
     * @param id The ID of the order.
     * @return The HTTP response with the order or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get order by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order found"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable("id") Long id) {
        Optional<OrderEntity> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete order by ID.
     *
     * @param id The ID or the order to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete order by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Order not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
