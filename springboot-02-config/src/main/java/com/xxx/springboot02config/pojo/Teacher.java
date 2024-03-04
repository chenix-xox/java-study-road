package com.xxx.springboot02config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Chenix
 * @create 2024-02-18 21:18
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String name;
    private Integer teachAge;
}
