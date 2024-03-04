package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 往Session中存储内容
 * @author Chenix
 * @create 2023-10-28 19:41
 */
public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // 获取Session
        HttpSession session = req.getSession();
        // 往Session存东西
        session.setAttribute("name", "陈Session");
        // 获取Session的id
        String sessionId = session.getId();

        if (session.isNew()){
            resp.getWriter().write("新创建的Session，id为：" + sessionId);
        }else {
            resp.getWriter().write("session已存在，id为：" + sessionId);
        }
    }
}
