package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 18:53
 * @Version 1.0
 */
@RestController
@Slf4j
public class OrderController {

    @Value("${payment.server.url}")
    private String paymentUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentZk(){
        String result = restTemplate.getForObject(paymentUrl+"/payment/zk", String.class);
        return result;
    }
}
