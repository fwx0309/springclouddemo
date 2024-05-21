package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 6:13
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    // ********* 服务降级
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("*****paymentInfo_OK*****" + serverPort +"id:"+ id + ",result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*****paymentInfo_TimeOut*****" + serverPort +"id:"+ id + ",result:" + result);
        return result;
    }

    // ********* 服务熔断
    @GetMapping("/payment/ciruit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String circuitBreaker = paymentService.paymentCircuitBreaker(id);
        log.info("*****paymentCircuitBreaker*****" + serverPort +"id:"+ id + ",result:" + circuitBreaker);
        return circuitBreaker;
    }
}
