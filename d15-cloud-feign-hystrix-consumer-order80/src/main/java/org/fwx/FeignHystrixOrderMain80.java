package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName FeignHystrixMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/19 7:34
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class FeignHystrixOrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixOrderMain80.class, args);
    }
}
