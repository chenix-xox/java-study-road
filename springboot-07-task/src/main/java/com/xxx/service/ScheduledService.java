package com.xxx.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Chenix
 * @create 2024-03-12 1:53
 */
@Service
public class ScheduledService {

    @Scheduled(cron = "0,1,2 3 2 * * ? ")
    public void hello() {
        System.out.println("执行了定时方法！");
    }

    @Scheduled(cron = "*/6 * * * * ?")
    public void sayHello() {
        System.out.println("hello");
    }
}
