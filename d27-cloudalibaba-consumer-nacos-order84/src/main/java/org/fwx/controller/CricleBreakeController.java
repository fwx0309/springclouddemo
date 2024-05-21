package org.fwx.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.fwx.entities.CommonResult;
import org.fwx.entities.Payment;
import org.fwx.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName CricleBreakeController
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/20 20:27
 * @Version 1.0
 */
@RestController
@Slf4j
public class CricleBreakeController {

    @Value("${payment-service.url}")
    private String serviceUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 该方法标记为 Sentinel Resource，用于处理某些特定的业务逻辑，并集成了Sentinel熔断和降级机制。
     *
     * @SentinelResource 注解的使用说明：
     *    - value: 指定资源名称，用于在Sentinel控制台进行监控和配置。
     *    - fallback: 指定异常时的回退方法，用于处理方法执行时抛出的非预期异常。
     *    - blockHandler: 指定当资源被限流、降级或隔离时的处理方法。
     *    - exceptionsToIgnore: 指定忽略的异常类型，这些异常在发生时不会触发fallback逻辑。
     */
    @GetMapping("/consumer/payment/{id}")
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",
            blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult commonResult = restTemplate.getForObject(serviceUrl + "/payment/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常" + id);
        } else if (commonResult.getData() == null) {
            throw new NullPointerException("NullPointerException, 空指针异常" + id);
        }

        return commonResult;
    }

    /**
     * fallback 异常兜底方法
     * @param id
     * @param e
     * @return
     */
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        return new CommonResult(444,"兜底异常handlerFallback,exception内容" + e.getMessage(),null);
    }

    /**
     * blockHandler 限流、熔断兜底方法
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException){
        return new CommonResult(445,"blockHandler-sentinel限流,无此流水: blockException" + blockException.getMessage(),null);
    }


    //**************** OpenFeign 调用 ****************
    @Autowired
    private PaymentService paymentService;

    /**
     * Feign 调用
     */
    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSql(id);
    }
}
