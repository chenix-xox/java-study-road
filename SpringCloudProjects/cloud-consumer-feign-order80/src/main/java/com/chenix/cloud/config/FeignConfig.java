package com.chenix.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chenix
 * @create_date 2024/4/22 20:56
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        // 默认不走重试策略
        return Retryer.NEVER_RETRY;
//        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
