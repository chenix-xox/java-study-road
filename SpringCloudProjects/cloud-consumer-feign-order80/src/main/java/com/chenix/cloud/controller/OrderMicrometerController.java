package com.chenix.cloud.controller;

import com.chenix.cloud.apis.PayFeignApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenix
 * @date 2024.07.30 21:30
 * @description 消费者 - 链路追踪测试业务类
 * Micrometer 替代 Sleuth
 */
@RestController
@Slf4j
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.myMicrometer(id);
    }
}
