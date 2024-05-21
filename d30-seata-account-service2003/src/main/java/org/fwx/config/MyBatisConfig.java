package org.fwx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisConfig
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/21 13:58
 * @Version 1.0
 */
@Configuration
@MapperScan("org.fwx.dao")
public class MyBatisConfig {
}
