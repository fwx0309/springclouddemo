package org.fwx.service;

import org.fwx.entities.Payment;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 10:15
 * @Version 1.0
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
