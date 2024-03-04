package com.xxx.controller;

import com.xxx.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenix
 * @create 2024-02-15 2:20
 */
@RestController
public class AjaxController {
    @RequestMapping("/ajax")
    public List<User> ajaxTest() {
        List<User> list = new ArrayList<>();
        list.add(new User("1", "1", "1"));
        list.add(new User("2", "2", "2"));
        list.add(new User("3", "3", "3"));
        list.add(new User("3", "4", "4"));
        System.out.println(list);
        return list;
    }
}
