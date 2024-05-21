package org.fwx.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeignConfig
 * @Description Feign 配置
 * @Author Fwx
 * @Date 2024/5/19 5:18
 * @Version 1.0
 */
@Configuration
public class FeignConfig {

    /**
     * feign 日志级别
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
