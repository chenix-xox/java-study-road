package com.xxx.springboot04security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Chenix
 * @create 2024-03-04 19:19
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能页只有对应权限的人才能访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限，默认到登陆页面
        http.formLogin().loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login");

        // 开启注销
        http.logout().logoutSuccessUrl("/");

        // 开启 记住我 功能
        http.rememberMe().rememberMeParameter("remember");
    }

    // 认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin1").password(new BCryptPasswordEncoder().encode("admin1")).roles("vip1", "vip3")
                .and()
                .withUser("admin2").password(new BCryptPasswordEncoder().encode("admin2")).roles("vip1", "vip2");
    }
}
