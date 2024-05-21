package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName NacosPaymentMain9001
 * @Description NacosPaymentMain9001类是应用程序的入口点。
 *              它利用Spring Boot框架启动和运行应用程序，并通过@EnableDiscoveryClient注解启用服务发现功能。
 * @Author Fwx
 * @Date 2024/5/19 21:30
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentMain9002 {
    public static void main(String[] args) {
        // 启动Spring Boot应用程序
        SpringApplication.run(NacosPaymentMain9002.class, args);
    }
}

