package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.CartDetailEntity;
import com.ownsprojects.ecomerce.persistence.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    @Autowired
    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public CartDetailEntity saveCartDetail(CartDetailEntity cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    public List<CartDetailEntity> getAllCartDetails() {
        return cartDetailRepository.findAll();
    }

    public CartDetailEntity getCartDetailById(Long id) {
        return cartDetailRepository.findById(id).orElse(null);
    }

    public void deleteCartDetail(Long id) {
        cartDetailRepository.deleteById(id);
    }
}
