package com.demo.eventlistener.utils;

import org.springframework.context.ApplicationEvent;

public class EventUtils {

    private EventUtils() {
    }

    public static void publishEvent(ApplicationEvent event) {
        BeanUtils.getApplicationContext().publishEvent(event);
    }
}
