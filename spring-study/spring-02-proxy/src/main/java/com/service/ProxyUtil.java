package com.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理
 *
 * @author Chenix
 * @create 2024-02-01 2:41
 */
public class ProxyUtil {
    public Object createProxy(Object target) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {
                        System.out.println("准备执行" + method.getName() + "方法");
                        Object res = method.invoke(target, args);
                        System.out.println("执行完成");
                        return res;
                    }
                });
    }
}
