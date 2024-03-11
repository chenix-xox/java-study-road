package com.xxx.controller;

import com.xxx.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenix
 * @create 2024-03-11 22:28
 */
@RestController
public class HelloController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/hello")
    public String hello() {
        asyncService.hello();
        return "OK";
    }
}
