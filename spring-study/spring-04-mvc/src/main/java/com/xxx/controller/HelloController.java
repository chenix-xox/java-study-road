package com.xxx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

/**
 * @author Chenix
 * @create 2024-02-12 0:50
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/h1", method = RequestMethod.GET)
    public String hello(Model model, HttpServletRequest request) {
        model.addAttribute("msg", "hello~~~");
        return "hello";
    }

    @GetMapping("/forward")
    public String forward(Model model) {
        model.addAttribute("msg", "转发");
        return "forward:/WEB-INF/jsp/hello.jsp";
//        return "hello";
    }

    @RequestMapping("/redirect")
    public String redirect(Model model) {
        model.addAttribute("msg", "重定向");
        return "redirect:/index.jsp";
//        return "index";
    }
}
