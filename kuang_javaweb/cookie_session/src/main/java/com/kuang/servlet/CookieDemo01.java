package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chenix
 * @create 2023-10-24 20:33
 */
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request是客户端发来的请求（比如web应用携带提交的表单内容...）
        // response是服务端回应的内容
        // 服务器告诉客户端，客户端来的时间，时间封装为一个cookie，下次客户端发起请求的时候，携带该cookie
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        // 服务端从客户端获取cookie：cookie可以有多个
        Cookie[] cookies = req.getCookies();
        // 判断cookie是否为空
        if (cookies != null) {
            // 如果cookie不为空，获取cookie里的值打印
            for (Cookie cookie : cookies) {
                if ("lastLoginTime".equals(cookie.getName())) {
                    long time = Long.parseLong(cookie.getValue());
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    out.write("上次访问时间是：" + date);
                }
            }
        } else {
            out.write("这是您第一次访问");
            // 第一次访问后，可以给与一个cookie
        }

        // 给一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");
        cookie.setMaxAge(30);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
