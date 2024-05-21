package org.fwx.controller;

import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @Value("${payment.server.url8003}")
    private String paymentServerUrl8003;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment")
    public CommonResult create(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(paymentServerUrl + "/payment", payment, CommonResult.class);
        log.info("*** 插入结果：" + commonResult.getData());
        return commonResult;
    }

    /**
     * 根据id查询，restTemplate.getForObject（）
     * @param id
     * @return
     */
    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        CommonResult commonResult = restTemplate.getForObject(paymentServerUrl + "/payment/" + id, CommonResult.class);
        log.info("*** 查询结果：" + commonResult.getData());
        return commonResult;
    }

    /**
     * 根据id查询，restTemplate.getForObject（）
     * @param id
     * @return
     */
    @GetMapping("/payment2/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(paymentServerUrl + "/payment/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "查询失败");
        }
    }

    /**
     * 根据id查询，http://CLOUD-PROVIDER-PAYMENT8003
     * @param id
     * @return
     */
    @GetMapping("/payment8003/{id}")
    public CommonResult<Payment> getPaymentById8003(@PathVariable Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(paymentServerUrl8003 + "/payment/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "查询失败");
        }
    }
}
