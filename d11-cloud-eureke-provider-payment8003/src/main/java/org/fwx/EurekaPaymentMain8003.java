package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PaymentMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 9:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient     // 启用Eureka客户端
@EnableDiscoveryClient // 启用服务发现
public class EurekaPaymentMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaPaymentMain8003.class,args);
    }
}
