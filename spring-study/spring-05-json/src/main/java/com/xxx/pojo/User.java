package com.xxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chenix
 * @create 2024-02-13 5:16
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String name;
    private Integer age;
    private String sex;
}
