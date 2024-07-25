package com.chenix.cloud.controller;

import com.chenix.cloud.apis.PayFeignApi;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenix
 * @date 2024.07.25 10:55
 * @description 消费者限流控制类
 */
@RestController
@Slf4j
public class OrderRateLimiterController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimitFallback")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return payFeignApi.myRateLimit(id);
    }

    public String myRatelimitFallback(Integer id, Throwable t) {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
