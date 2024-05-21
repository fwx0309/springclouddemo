package org.ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RibbonConfig
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/18 21:29
 * @Version 1.0
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IRule setIRule(){
        return new RandomRule();
    }
}
