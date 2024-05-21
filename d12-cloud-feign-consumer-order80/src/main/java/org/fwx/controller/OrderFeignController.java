package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderFeignController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 23:18
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> payment = paymentFeignService.getPaymentById(id);
        log.info("*****查询结果：{}",payment);
        return payment;
    }
}
