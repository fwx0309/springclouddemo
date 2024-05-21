package org.fwx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AppConfig
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 11:27
 * @Version 1.0
 */
@Configuration
public class AppConfig {

    /**
     * 创建 RestTemplate 实例，注入容器
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
