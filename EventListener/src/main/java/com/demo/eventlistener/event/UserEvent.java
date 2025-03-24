package com.demo.eventlistener.event;

import com.demo.eventlistener.pojo.User;
import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    public UserEvent(User user) {
        super(user);
    }
}
