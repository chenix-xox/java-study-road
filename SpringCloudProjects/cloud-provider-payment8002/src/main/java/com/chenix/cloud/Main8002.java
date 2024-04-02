package com.chenix.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Chenix
 * @create_date 2024/4/2 0:26
 */
@SpringBootApplication
@MapperScan("com.chenix.cloud.mapper")
@EnableDiscoveryClient
@RefreshScope
public class Main8002{
    public static void main(String[] args) {
        SpringApplication.run(Main8002.class);
    }
}