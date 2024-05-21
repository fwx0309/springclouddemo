package org.fwx.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.fwx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderFeignHystrixController2
 * @Description 1. OrderFeignHystrixController2（不做处理），PaymentService 降级处理方法
 *              2. 这种方式针对的是调用服务的时候
 * @Author Fwx
 * @Date 2024/5/19 7:38
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderFeignHystrixController2 {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer2/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("*******result: " + result);
        return result;
    }

    @GetMapping("/consumer2/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*******result: " + result);
        return result;
    }
}
