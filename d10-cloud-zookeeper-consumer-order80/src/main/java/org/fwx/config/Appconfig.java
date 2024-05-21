package org.fwx.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName Appconfig
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 18:52
 * @Version 1.0
 */
@Configuration
public class Appconfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
