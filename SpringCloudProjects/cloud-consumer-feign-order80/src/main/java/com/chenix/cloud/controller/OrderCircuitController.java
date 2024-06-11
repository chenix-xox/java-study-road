package com.chenix.cloud.controller;

import com.chenix.cloud.apis.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenix
 * @date 2024.06.06 17:26
 * @description 断路器测试controller
 */
@RestController
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;


}
