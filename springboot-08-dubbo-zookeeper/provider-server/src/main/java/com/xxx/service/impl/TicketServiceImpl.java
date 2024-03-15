package com.xxx.service.impl;

import com.xxx.service.TicketService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author Chenix
 * @create 2024-03-13 16:03
 */
@DubboService
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket(String name) {
        return "《 " + name + "》";
    }
}
