package com.chenix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chenix
 * @create_date 2024/4/25 22:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Child extends Parent {
    private String childName;
}
