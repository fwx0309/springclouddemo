package org.fwx.service;

import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName Paymentservice
 * @Description 定义了一个支付服务的Feign接口，用于远程调用支付服务。
 *              使用@FeignClient注解指定了服务的名称和Fallback服务。
 * @Author Fwx
 * @Date 2024/5/20 22:04
 * @Version 1.0
 */
@FeignClient(value = "${payment-service.name}",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id);
}

