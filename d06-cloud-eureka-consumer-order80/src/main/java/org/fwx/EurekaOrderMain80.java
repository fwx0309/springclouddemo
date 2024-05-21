package org.fwx;

import org.ribbonconfig.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @ClassName OrderMain
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 11:23
 * @Version 1.0
 */
@SpringBootApplication
@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT111",configuration = RibbonConfig.class)
public class EurekaOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderMain80.class,args);
    }
}
