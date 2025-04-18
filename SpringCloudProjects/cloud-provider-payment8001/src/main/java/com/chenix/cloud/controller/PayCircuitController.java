package com.chenix.cloud.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author chenix
 * @date 2024.06.06 15:54
 * @description 断路器控制器
 */
@RestController
@Slf4j
public class PayCircuitController {
    //=========Resilience4j CircuitBreaker 的例子
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        log.info("8001!!!!!!!!!!!!!!!");
        if (id == -4) {
            throw new RuntimeException("----circuit id 不能负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello【8001】, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }
}
