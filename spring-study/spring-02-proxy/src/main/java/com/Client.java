package com;

import com.service.ProxyUtil;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

/**
 * @author Chenix
 * @create 2024-02-01 2:31
 */
public class Client {
    public static void main(String[] args) {
        ProxyUtil proxyUtil = new ProxyUtil();
        UserService userServiceProxy = (UserService) proxyUtil.createProxy(new UserServiceImpl());
        userServiceProxy.add();
    }
}
