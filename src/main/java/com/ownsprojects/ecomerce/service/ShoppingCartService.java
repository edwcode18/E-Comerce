package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.ShoppingCartEntity;
import com.ownsprojects.ecomerce.persistence.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCartEntity saveShoppingCart(ShoppingCartEntity shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCartEntity> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCartEntity getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
