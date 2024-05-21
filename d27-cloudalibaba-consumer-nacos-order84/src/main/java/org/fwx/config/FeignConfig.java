package org.fwx.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName FeignConfig
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/20 20:21
 * @Version 1.0
 */
@Configuration
public class FeignConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
