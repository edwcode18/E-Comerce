package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.DiscountEntity;
import com.ownsprojects.ecomerce.persistence.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public DiscountEntity saveDiscount(DiscountEntity discount) {
        return discountRepository.save(discount);
    }

    public List<DiscountEntity> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public DiscountEntity getDiscountById(Long id) {
        return discountRepository.findById(id).orElse(null);
    }

    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
