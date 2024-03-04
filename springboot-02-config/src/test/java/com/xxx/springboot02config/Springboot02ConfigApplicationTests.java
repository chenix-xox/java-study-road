package com.xxx.springboot02config;

import com.xxx.springboot02config.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02ConfigApplicationTests {

    @Autowired
    private Student student;

    @Test
    void contextLoads() {
        System.out.println(student);
    }

}
