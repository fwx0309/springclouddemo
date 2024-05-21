package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PaymentMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 9:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaPaymentMain8002.class,args);
    }
}
