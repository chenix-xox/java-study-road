package com.service.impl;

import com.service.UserService;

/**
 * @author Chenix
 * @create 2024-02-01 2:40
 */
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("新增");
    }

    @Override
    public void delete() {
        System.out.println("删除");

    }

    @Override
    public void update() {
        System.out.println("更新");
    }

    @Override
    public void query() {
        System.out.println("查询");
    }
}
