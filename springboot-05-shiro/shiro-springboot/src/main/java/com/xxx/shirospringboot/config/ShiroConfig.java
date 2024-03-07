package com.xxx.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Chenix
 * @create 2024-03-06 0:18
 */
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean：3
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(securityManager);

        // 添加shiro内置过滤器
        /*
         * anon：无需认证即可访问
         * authc：必须认证了才能访问
         * user：必须拥有 “记住我” 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         * */
        // 拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 授权，正常情况下，没有授权会跳转到未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登陆的请求
        bean.setLoginUrl("/toLogin");

        // 设置未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    // DefaultWebSecurityManager：2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联userRealm,管理userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建realm对象，需要自定义类：1
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
