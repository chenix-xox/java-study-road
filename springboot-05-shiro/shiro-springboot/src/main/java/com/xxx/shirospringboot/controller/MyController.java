package com.xxx.shirospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        System.out.println("username：" + username);
        System.out.println("password：" + password);
        return "登陆成功！";
    }
}
