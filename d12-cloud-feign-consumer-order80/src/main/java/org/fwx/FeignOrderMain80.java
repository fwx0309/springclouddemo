package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName FeignOrderMain80
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 23:12
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class FeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderMain80.class,args);
    }
}
