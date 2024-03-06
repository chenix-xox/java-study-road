package com.xxx.shirospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Chenix
 * @create 2024-03-05 20:24
 */
@Controller
public class MyController {


    @RequestMapping({"/","/index"})
    public String toIndex(Model model)
    {
        model.addAttribute("msg","welcome to Shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }
}
