package com.chenix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenix.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Book;

/**
 * @author Chenix
 * @create_date 2024/5/1 21:33
 */
public interface BooksMapper extends BaseMapper<Books> {

    Page<Books> selectPageByPrice(@Param("pageParam") Page<Books> pageParam,
                                  @Param("min") Integer min,
                                  @Param("max") Integer max);
}
