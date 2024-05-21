package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName SeataAccountMain2003
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 13:44
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 排除数据源，用seata数据源
@EnableDiscoveryClient
@EnableFeignClients
public class SeataAccountMain2003 {
    public static void main(String[] args) {
       SpringApplication.run(SeataAccountMain2003.class, args);
    }
}
