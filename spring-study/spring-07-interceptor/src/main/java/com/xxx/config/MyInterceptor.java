package com.xxx.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Chenix
 * @create 2024-02-15 19:53
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("处理前...");
        HttpSession session = request.getSession();


        if (request.getRequestURI().contains("goLogin")) {
            // 如果是要去登陆页面，允许通过
            return true;
        }

        if (request.getRequestURI().contains("login")) {
            // 如果是要登陆，允许通过
            return true;
        }

        if (!StringUtils.isEmpty(session.getAttribute("username"))) {
            // username不为空，说明是已登录，允许通过
            System.out.println("查询到了username：" + session.getAttribute("username"));
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/goLogin.jsp").forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("处理后...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        System.out.println("清理...");
    }
}
