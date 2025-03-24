package com.demo.eventlistener.web;

import com.demo.eventlistener.event.UserEvent;
import com.demo.eventlistener.pojo.User;
import com.demo.eventlistener.utils.EventUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping
    public void publishEvent() {
        log.info("准备发送事件");
        EventUtils.publishEvent(new UserEvent(new User("陈多多", 18)));
        log.info("事件发送完毕");
    }
}
