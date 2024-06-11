package com.chenix.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 * @author Chenix
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MainOpenFeign80
{
    public static void main(String[] args){
        SpringApplication.run(MainOpenFeign80.class,args);
        System.out.println("OpenFeign80 start success!");
    }
}
