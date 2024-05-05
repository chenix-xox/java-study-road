package com.chenix;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import com.chenix.service.BooksService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
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

        System.out.println("==============TAT==============");

        String bookName = "传";
        LambdaQueryWrapper<Books> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(bookName), Books::getBookName, bookName);

        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(bookName),"bookName",bookName);
        List<Books> books = booksMapper.selectList(lambdaQueryWrapper);
        books.forEach(System.out::println);
    }
}
