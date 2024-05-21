package org.fwx.service.impl;

import org.fwx.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentServiceImpl
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 10:22
 * @Version 1.0
 */
@Service
public class PaymentFallbackServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackServiceImpl -- paymentInfo_OK fallback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackServiceImpl -- paymentInfo_TimeOut fallback";
    }
}
