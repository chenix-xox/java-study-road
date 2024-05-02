package com.chenix.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author Chenix
 * @create_date 2024/5/1 21:22
 */
@Data
@TableName("t_book")
public class Books {
    @TableId(type = IdType.AUTO)
    @OrderBy
    private Long id;
    @TableField("bookImg")
    private String bookImg;
    @TableField("bookName")
    private String bookName;
    private Double price;
    private String author;
    @TableField("saleCount")
    private Integer saleCount;
    @TableField("bookCount")
    private Integer bookCount;
}
