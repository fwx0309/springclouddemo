package org.fwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ZkOrderMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 18:51
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZkOrderMain {
    public static void main(String[] args) {
       SpringApplication.run(ZkOrderMain.class, args);
    }
}
