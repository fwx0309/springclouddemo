package org.fwx.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 6:11
 * @Version 1.0
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }


    /**
     * 获取支付信息的方法，此方法上添加了HystrixCommand注解以处理降级的情况。
     * 方法执行时会模拟一个延时以模拟网络请求或其他耗时操作。
     *
     * @param id 交易的唯一标识符。
     * @return 返回一个包含线程名和交易ID的字符串信息。
     * @see HystrixCommand 用于标记方法，使其具有熔断和超时处理的能力。
     * @see HystrixProperty 用于设置HystrixCommand的属性，如超时时间等。
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "10000")
    })
    public String paymentInfo_TimeOut(Integer id){
        // 报错降级
//        int i = 10/0;
        try {
            // 模拟一个5秒的延时
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 返回当前线程名和交易ID的信息
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "\t";
    }

    /**
     * 获取支付信息的方法，此方法上添加了HystrixCommand注解方法（paymentInfo_TimeOut()）以处理降级的情况。
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler,id: " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 服务熔断
     * 通过HystrixCommand注解实现的支付断路器方法，旨在防止服务雪崩。
     * 当请求失败率达到一定阈值时，断路器将打开，接下来的请求将直接走Fallback方法，
     * 从而避免请求堆积导致服务不可用。
     *
     * @param id 交易的唯一标识，不能为负数。
     * @return 返回支付成功的信息，包括当前线程名和生成的流水号。
     * @throws RuntimeException 如果id为负数，抛出此异常。
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_handler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 开启断路器功能
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 需要至少10个请求才能统计失败率
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 断路器打开后，在此时间段内不会尝试恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 当失败率超过60%时，断路器打开
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.randomUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    /**
     * 断路器打开时调用的Fallback方法，用于处理异常或服务不可用的情况。
     *
     * @param id 交易的唯一标识。
     * @return 返回错误提示信息。
     */
    public String paymentCircuitBreaker_handler(Integer id){
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

}
