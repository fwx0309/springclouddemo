package org.fwx.controller;


import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/20 20:02
 * @Version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"from mysql,serverPort: "+serverPort,payment);
    }
}
