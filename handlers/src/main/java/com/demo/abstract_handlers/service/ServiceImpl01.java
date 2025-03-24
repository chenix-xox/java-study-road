package com.demo.abstract_handlers.service;


import com.demo.abstract_handlers.handler.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ServiceImpl01 {


    @Resource
    private List<CommandHandler> handlers;


    public void handle(String name) {
        for (CommandHandler handler : handlers) {
            log.info("handler:{}", handler.getClass().getSimpleName());
            System.out.println(handler.handle(name));
        }
    }
}
