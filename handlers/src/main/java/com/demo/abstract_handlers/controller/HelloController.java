package com.demo.abstract_handlers.controller;


import com.demo.abstract_handlers.service.ServiceImpl01;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    private ServiceImpl01 service;

    @RequestMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        service.handle(name);
        return "hello";
    }
}
