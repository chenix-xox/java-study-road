package com.chenix;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import com.chenix.service.BooksService;
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

    @Autowired
    private BooksService booksService;
    @Test
    public void selectListTest(){
        QueryWrapper<Books> booksQueryWrapper = new QueryWrapper<>();
        booksQueryWrapper.like("bookName","%语言%");
        // 查询所有数据
        List<Books> userList = booksMapper.selectList(booksQueryWrapper);
        userList.forEach(System.out::println);
        System.out.println("=============QAQ==============");
        Books byIdBook = booksService.getById(1);
        System.out.println(byIdBook);
    }
}
