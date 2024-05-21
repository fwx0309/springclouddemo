package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 10:23
 * @Version 1.0
 */
@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult add(@RequestBody Payment payment){
        int resoult = paymentService.create(payment);
        log.info("*** 插入结果：" + resoult);

        if (resoult > 0){
            return new CommonResult(200,"插入成功, 端口号" + serverPort,resoult);
        } else {
            return new CommonResult(400,"插入失败, 端口号" + serverPort,resoult);
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        Payment resoult = paymentService.getPaymentById(id);
        log.info("*** 查询结果：" + resoult);

        if (resoult != null){
            return new CommonResult(200,"查询成功, 端口号" + serverPort,resoult);
        } else {
            return new CommonResult(400,"查询失败, 端口号" + serverPort,resoult);
        }
    }
}
