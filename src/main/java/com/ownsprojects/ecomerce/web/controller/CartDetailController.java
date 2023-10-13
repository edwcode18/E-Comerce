package com.ownsprojects.ecomerce.web.controller;

import com.ownsprojects.ecomerce.persistence.entity.CartDetailEntity;
import com.ownsprojects.ecomerce.persistence.entity.CustomerEntity;
import com.ownsprojects.ecomerce.service.CartDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cartDetails")
public class CartDetailController {
    private final CartDetailService cartDetailService;

    @Autowired
    public CartDetailController(CartDetailService cartDetailService) {
        this.cartDetailService = cartDetailService;
    }

    @PostMapping
    public ResponseEntity<CartDetailEntity> createCartDetail(@RequestBody CartDetailEntity cartDetail) {
        try {
            return new ResponseEntity<>(cartDetailService.saveCartDetail(cartDetail), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CartDetailEntity>> getAllCartDetails() {
        try {
            return new ResponseEntity<>(cartDetailService.getAllCartDetails(), HttpStatus.FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDetailEntity> getCartDetail(@PathVariable("id") Long id) {
        Optional<CartDetailEntity> cartDetail = cartDetailService.getCartDetailById(id);
        return cartDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartDetail(@PathVariable("id") Long id) {
        try {
            cartDetailService.deleteCartDetail(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error durante la eliminación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error durante la eliminación: " + e.getMessage());
        }
    }
}
