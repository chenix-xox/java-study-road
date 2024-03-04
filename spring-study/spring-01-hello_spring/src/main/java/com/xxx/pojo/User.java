package com.xxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chenix
 * @create 2024-01-31 1:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String pwd;
}
