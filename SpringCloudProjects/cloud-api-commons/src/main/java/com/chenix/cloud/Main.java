package com.chenix.cloud;

import java.time.ZonedDateTime;

/**
 * @author Chenix
 * @create_date 2024/3/28 22:16
 */
public class Main {
    public static void main(String[] args) {
        // 默认时区
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
    }
}