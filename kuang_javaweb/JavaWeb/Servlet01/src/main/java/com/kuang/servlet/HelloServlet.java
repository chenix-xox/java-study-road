package com.kuang.servlet;

import com.sun.xml.internal.ws.api.policy.PolicyResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Chenix
 * @create 2023-10-19 11:40
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.addHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("你好Hello");
        getServletContext().setAttribute("name", "陈多多");


    }
}
