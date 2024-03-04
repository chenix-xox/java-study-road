package com.kuang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chenix
 * @create 2023-10-24 14:39
 */
public class test01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("成功！");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username + ",password:" + password);
    }
}
