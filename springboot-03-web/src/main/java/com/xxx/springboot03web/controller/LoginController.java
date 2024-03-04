package com.xxx.springboot03web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * @author Chenix
 * @create 2024-02-21 21:51
 */
@Controller
@RequestMapping("/user")
public class LoginController {


    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("pwd") String pwd,
                        HttpSession session,
                        Model model) {
        if (Objects.equals(username, "a") && Objects.equals(pwd, "a")) {
            // 登陆成功
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误！");
            return "index";
        }

    }

}
