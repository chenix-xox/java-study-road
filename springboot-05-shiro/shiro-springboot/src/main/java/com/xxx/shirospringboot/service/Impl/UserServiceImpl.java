package com.xxx.shirospringboot.service.Impl;

import com.xxx.shirospringboot.mapper.UserMapper;
import com.xxx.shirospringboot.pojo.User;
import com.xxx.shirospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Chenix
 * @create 2024-03-06 23:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        User user = userMapper.queryUserByName(name);
        if (!StringUtils.isEmpty(user)) {
            System.out.println("user：" + user);
            System.out.println("id：" + user.getId());
            System.out.println("user：" + user.getName());
            System.out.println("pwd：" + user.getPwd());
        }
        return user;
    }
}
