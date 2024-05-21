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
 * @ClassName OrderFeignHystrixController1
 * @Description 1.该 controller 上配置 @DefaultProperties 默认降级处理方法，
 *              2.接口方法上配置 @HystrixCommand，没有配置 fallbackMethod = "paymentInfo_TimeOutHandler"，则使用 @DefaultProperties 配置的降级处理方法
 *              3.接口方法上没有配置 @HystrixCommand 不做降级处理方法，
 *              4.默认降级处理方法与接口方法配置的降级处理（fallbackMethod = "paymentInfo_TimeOutHandler"）方法冲突，则使用接口方法配置的降级处理方法
 *              ps:更优解请看 OrderFeignHystrixController2（不做处理），PaymentService1 降级处理方法
 * @Author Fwx
 * @Date 2024/5/19 7:38
 * @Version 1.0
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderFeignHystrixController1 {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer1/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        // 模拟操作出错导致的降级
        int i = 10 / 0;
        String result = paymentService.paymentInfo_OK(id);
        log.info("*******result: " + result);
        return result;
    }

    /**
     * 通过指定ID获取支付信息，此方法通过HystrixCommand注解进行了超时和降级处理。
     * 当请求执行超时（设定超时时间为1500毫秒）或服务提供方不可用时，会自动调用降级方法。
     *
     * @param id 支付的唯一标识符
     * @return 返回支付信息的字符串表示
     */
    @GetMapping("/consumer1/payment/hystrix/timeout/{id}")
    // 配置HystrixCommand注解，指定降级处理方法
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        // 模拟操作出错导致的降级
        // int i = 10 / 0;
        try {
            // 模拟服务调用超时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*******result: " + result);
        return result;
    }

    @GetMapping("/consumer1/payment/hystrix/timeout1/{id}")
    // 配置HystrixCommand注解，指定降级处理方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentInfo_TimeOut1(@PathVariable("id") Integer id) {
        // 模拟操作出错导致的降级
        // int i = 10 / 0;
        try {
            // 模拟服务调用超时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*******result: " + result);
        return result;
    }

    /**
     * 指定的降级处理方法
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "我是消费者80，对方支付系统繁忙，请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }

    /**
     * 全局降级处理方法
     * @return
     */
    public String paymentGlobalFallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
