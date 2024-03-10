package com.xxx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenix
 * @create 2024-03-08 21:40
 */
@RestController
@RequestMapping("user")
@Tag(name = "Hello控制类", description = "哈喽的控制类！")
public class HelloController {

    @GetMapping("/hello")
    @Operation(description = "哈喽接口")
    public String hello(){
        return "helloSwagger111";
    }
}


