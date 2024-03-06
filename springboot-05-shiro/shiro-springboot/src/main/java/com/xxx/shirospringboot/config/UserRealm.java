package com.xxx.shirospringboot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * 自定义的UserRealm
 *
 * @author Chenix
 * @create 2024-03-06 0:26
 */
public class UserRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        // 登录就会走这边
        // 验证用户名和密码，一般从数据库中取
        // 此处token对应登录Controller中生成的token，可以强转取值比较
        String username = "admin";
        String password = "admin";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (userToken.getUsername().equals(username)) {
            // 抛出异常：UnknownAccountException
            return null;
        }
        return new SimpleAuthenticationInfo("", password, "");
    }
}
