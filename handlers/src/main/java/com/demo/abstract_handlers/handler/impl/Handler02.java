package com.demo.abstract_handlers.handler.impl;

import com.demo.abstract_handlers.handler.CommandHandler;
import org.springframework.stereotype.Component;


@Component
public class Handler02 extends CommandHandler {
    @Override
    public String handle(String name) {
        return "handler02" + name;
    }
}
