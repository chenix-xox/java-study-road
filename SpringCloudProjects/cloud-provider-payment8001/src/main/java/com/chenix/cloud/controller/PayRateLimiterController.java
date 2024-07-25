package com.chenix.cloud.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenix
 * @date 2024.07.25 10:51
 * @description 限流控制类
 */
@RestController
@Slf4j
public class PayRateLimiterController {
    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "[8001]Hello, myRateLimit欢迎到来 inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }
}
