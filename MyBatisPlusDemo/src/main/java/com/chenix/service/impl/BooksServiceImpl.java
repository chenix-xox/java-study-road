package com.chenix.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenix.mapper.BooksMapper;
import com.chenix.pojo.Books;
import com.chenix.service.BooksService;
import org.springframework.stereotype.Service;

/**
 * @author Chenix
 * @create_date 2024/5/2 23:47
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {
}
