package com.chenix;

import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 乐观锁测试
 *
 * @author Chenix
 * @create_date 2024/5/6 14:01
 */
@SpringBootTest
public class MyBatisPlusOptimisticLockerTest {
    @Autowired
    private BooksMapper booksMapper;

    @Test
    public void test01(){
        // 员工A查询书籍信息
        Books bookA = booksMapper.selectById(1);
        // 员工B查询书籍信息
        Books bookB = booksMapper.selectById(1);

        bookA.setPrice(bookA.getPrice() + 50);
        bookB.setPrice(bookB.getPrice() - 30);

        booksMapper.updateById(bookA);
        booksMapper.updateById(bookB);

        System.out.println(booksMapper.selectById(1));
    }
}
