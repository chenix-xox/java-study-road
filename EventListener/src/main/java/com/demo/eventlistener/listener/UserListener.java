package com.demo.eventlistener.listener;

import com.demo.eventlistener.event.UserEvent;
import com.demo.eventlistener.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserListener implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent event) {
        ThreadPoolUtils.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("用户事件监听器监听到事件：{}", event.getSource());
        });

    }
}
