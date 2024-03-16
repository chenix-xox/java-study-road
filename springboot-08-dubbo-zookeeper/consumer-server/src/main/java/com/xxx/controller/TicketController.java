package com.xxx.controller;

import com.xxx.service.TicketService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenix
 * @create 2024-03-16 19:05
 */
public class TicketController {

    @DubboReference
    TicketService ticketService;

    @GetMapping("/getTicket")
    public String getTicket(){
        return ticketService.getTicket("你好");
    }
}
