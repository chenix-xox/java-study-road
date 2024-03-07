package com.xxx.shirospringboot.config;

import com.xxx.shirospringboot.pojo.User;
import com.xxx.shirospringboot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * 自定义的UserRealm
 *
 * @author Chenix
 * @create 2024-03-06 0:26
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (!StringUtils.isEmpty(user.getPerms())){
            info.addStringPermission(user.getPerms());
        }

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        // 连接数据库
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if (StringUtils.isEmpty(user)) {
            return null;
        }

        // 密码认证：shiro完成，加密
        // 可以加密 -> md5 / md5盐
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
