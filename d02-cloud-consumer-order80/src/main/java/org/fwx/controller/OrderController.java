package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 11:24
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    @Value("${payment.server.url}")
    private String paymentServerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment")
    public CommonResult create(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(paymentServerUrl + "/payment", payment, CommonResult.class);
        log.info("*** 插入结果：" + commonResult.getData());
        return commonResult;
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        CommonResult commonResult = restTemplate.getForObject(paymentServerUrl + "/payment/" + id, CommonResult.class, CommonResult.class);
        log.info("*** 查询结果：" + commonResult.getData());
        return commonResult;
    }
}
