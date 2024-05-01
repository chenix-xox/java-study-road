package com.chenix;

import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyBatisPlusDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private BooksMapper booksMapper;
    @Test
    public void selectListTest(){
        // 查询所有数据
        List<Books> userList = booksMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
