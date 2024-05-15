package com.chenix.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chenix.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chenix
 * @create_date 2024/5/6 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {
    private Integer id;

    private String uname;

    private String pwd;

    private String email;

    private String role;

    private SexEnum sex;
}
