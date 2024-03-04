package com.xxx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxx.pojo.User;
import com.xxx.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chenix
 * @create 2024-02-13 5:13
 */
@Controller
public class JsonController {
    @RequestMapping("/jackJson")
    @ResponseBody
    public String jackJson() {
        Date date = new Date();
        return JsonUtils.getJson(date, "yyyy-MM-dd hh:mm:ss");
    }

    @RequestMapping("/fastJson")
    @ResponseBody
    public String fastJason() {
        List<User> users = new ArrayList<>();
        users.add(new User("xxx1", 1, "男"));
        users.add(new User("xxx2", 2, "男"));
        users.add(new User("xxx3", 3, "男"));
        users.add(new User("xxx4", 4, "男"));
        users.add(new User("xxx5", 5, "男"));
        users.add(new User("xxx6", 6, "男"));

        // java数据类型转json字符串
        // list转json
        String jsonStr1 = JSON.toJSONString(users);
        System.out.println("list转json字符串：" + jsonStr1);
        // 对象转json
        String jsonStr2 = JSON.toJSONString(new User("xxx1", 1, "男"));
        System.out.println("对象转json字符串：" + jsonStr2);


        // json转java对象
        User user1 = JSON.parseObject(jsonStr2, User.class);
        System.out.println("json转java对象：" + user1);


        // java对象转JSON对象
        JSONObject jsonObject = (JSONObject) JSON.toJSON(new User("xxx1", 1, "男"));
        System.out.println("java对象转JSON对象：" + jsonObject);


        // json对象转java对象
        User user2 = JSON.toJavaObject(jsonObject, User.class);
        System.out.println("json对象转java对象：" + user2);


        return null;
    }


    @PostMapping("/testInput")
    public String testInput(User user, Model model) {
        System.out.println(user);
        model.addAttribute("msg", "用户信息为：" + user);
        return "msgTest";
    }
}
