package com.chenix;

import com.chenix.mapper.UserMapper;
import com.chenix.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Chenix
 * @create_date 2024/5/6 14:35
 */
@SpringBootTest
public class MyBatisPlusEnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
