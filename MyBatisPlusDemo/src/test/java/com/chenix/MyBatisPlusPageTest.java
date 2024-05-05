package com.chenix;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Chenix
 * @create_date 2024/5/5 23:12
 */
@SpringBootTest
public class MyBatisPlusPageTest {
    @Autowired
    private BooksMapper booksMapper;

    @Test
    public void test01() {
        Page<Books> page = new Page<>(2, 3);
        booksMapper.selectPage(page, null);
        System.out.println(page.getRecords());

        System.out.println("===================QAQ");
        booksMapper.selectPageByPrice(page, 10, 30);
        System.out.println(page.getRecords());
    }
}
