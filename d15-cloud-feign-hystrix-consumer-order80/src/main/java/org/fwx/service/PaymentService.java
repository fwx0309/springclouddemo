package org.fwx.service;

import org.fwx.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName PaymentService
 * @Description 这方式：使用 Feign 调用控制超时配置yml
 *  feign:
 *   client:
 *     config:
 *       default:  # 默认配置
 *         connect-timeout: 10000
 *         read-timeout: 10000
 *   hystrix:  # 开启feign的hystrix支持
 *     enabled: true
 *
 * # 这里的超时必须和上面一起使用才生效
 * # ！！！feign:client:config:default:connect-timeout: 10000
 * #                                   read-timeout: 10000
 * # 配置feign的hystrix超时时间，默认是1s，这里设置10s
 * hystrix:
 *   command:
 *     default:
 *       execution:
 *         isolation:
 *           thread:
 *             timeoutInMilliseconds: 10000
 *
 * @Author Fwx
 * @Date 2024/5/19 7:35
 * @Version 1.0
 */
@Service
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT8005",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
