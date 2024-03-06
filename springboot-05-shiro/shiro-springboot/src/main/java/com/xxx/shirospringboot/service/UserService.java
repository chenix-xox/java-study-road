package com.xxx.shirospringboot.service;

import com.xxx.shirospringboot.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Chenix
 * @create 2024-03-06 23:40
 */
public interface UserService {
    User queryUserByName(@Param("name") String name);
}
