package com.xxx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Chenix
 * @create 2024-02-15 19:52
 */
@Controller
public class XxxController {
    @RequestMapping("/login")
    public String login(HttpSession session, String username, Model model) {
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "main";
    }

    @RequestMapping("/goOut")
    public String goOut(HttpSession session) {
        session.removeAttribute("username");
        return "goLogin";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/goLogin")
    public String goLogin() {
        return "goLogin";
    }
}
