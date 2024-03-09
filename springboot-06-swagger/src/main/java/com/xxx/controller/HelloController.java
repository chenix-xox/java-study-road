package com.xxx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenix
 * @create 2024-03-08 21:40
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "helloSwagger111";
    }
}
