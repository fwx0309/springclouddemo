package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName NacosOrderMain83
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 21:53
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderMain83 {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain83.class, args);
    }
}
