package com.xxx;

import com.xxx.service.TicketService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerApplicationTests {

    @DubboReference
    TicketService ticketService;

    @Test
    void contextLoads() {
        System.out.println(ticketService.getTicket("哈喽"));
    }

}
