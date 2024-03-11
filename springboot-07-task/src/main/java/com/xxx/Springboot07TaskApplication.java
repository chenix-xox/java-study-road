package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class Springboot07TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot07TaskApplication.class, args);
    }
}
