package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ZkPaymentMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 17:38
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZkPaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(ZkPaymentMain.class, args);
    }
}
