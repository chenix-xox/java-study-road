package com.kuang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Chenix
 * @create 2023-10-19 13:50
 */
public class GetContext extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.addHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        Object name = getServletContext().getAttribute("name");
        writer.println("你好Hello + " + name);
    }
}
