package com.ownsprojects.ecomerce.service;

import com.ownsprojects.ecomerce.persistence.entity.PaymentMethodEntity;
import com.ownsprojects.ecomerce.persistence.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethodEntity savePaymentMethod(PaymentMethodEntity paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public List<PaymentMethodEntity> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethodEntity getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
