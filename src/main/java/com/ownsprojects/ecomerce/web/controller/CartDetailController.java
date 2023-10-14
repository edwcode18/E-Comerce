package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.CartDetailEntity;
import com.ownsprojects.ecomerce.service.CartDetailService;
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
 * Controller for managing cart details.
 */
@Slf4j
@RestController
@Api(value = "CartDetailsController")
@RequestMapping("/cartDetails")
public class CartDetailController {
    private final CartDetailService cartDetailService;

    @Autowired
    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    /**
     * Create new cart detail.
     *
     * @param cartDetail The cart detail data to create.
     * @return The HTTP response with the cart detail created.
     */
    @ApiOperation(value = "Create new cart detail")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cart detail created"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<CartDetailEntity> createCartDetail(@Valid @RequestBody CartDetailEntity cartDetail) {
        CartDetailEntity createdCartDeatil = cartDetailService.saveCartDetail(cartDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartDeatil);
    }

    /**
     * Get all the cart details.
     *
     * @return The HTTP response with the list of the cart details.
     */
    @ApiOperation(value = "Get all the cart details")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart details found"),
            @ApiResponse(code = 404, message = "Cart details not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping
    public ResponseEntity<List<CartDetailEntity>> getAllCartDetails() {
        List<CartDetailEntity> cartsDetails = cartDetailService.getAllCartDetails();
        return ResponseEntity.status(cartsDetails.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(cartsDetails);
    }

    /**
     * Get cart detail by ID.
     *
     * @param id The ID of the cart detail.
     * @return The HTTP response with the cart detail or NOT_FOUND if not found.
     */
    @ApiOperation(value = "Get cart detail by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart detail found"),
            @ApiResponse(code = 404, message = "Cart detail not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CartDetailEntity> getCartDetail(@PathVariable("id") Long id) {
        Optional<CartDetailEntity> cartDetail = cartDetailService.getCartDetailById(id);
        return cartDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete cart detail by ID.
     *
     * @param id The ID or the cart detail to delete.
     * @return The HTTP response indicating the success of the operation.
     */
    @ApiOperation(value = "Delete cart detail by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "success operation"),
            @ApiResponse(code = 404, message = "Cart detail not found"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartDetail(@PathVariable("id") Long id) {
        cartDetailService.deleteCartDetail(id);
        return ResponseEntity.noContent().build();
    }
}