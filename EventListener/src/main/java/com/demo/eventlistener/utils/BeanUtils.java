package com.demo.eventlistener.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Service
@Slf4j
public class BeanUtils implements ApplicationContextAware, BeanFactoryAware, ServletContextAware {

    @Getter
    private static ApplicationContext applicationContext;
    private static BeanFactory beanFactory;
    private static ServletContext servletContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        BeanUtils.servletContext = servletContext;
    }
}
