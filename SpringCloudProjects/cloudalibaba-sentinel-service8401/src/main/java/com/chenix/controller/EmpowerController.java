package com.chenix.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenix
 * @date 2024.08.13 00:43
 * @description 授权控制类
 */
@RestController
@Slf4j
public class EmpowerController {
    @GetMapping(value = "/empower")
    public String requestSentinel4() {
        log.info("测试Sentinel授权规则empower");
        return "Sentinel授权规则";
    }
}
