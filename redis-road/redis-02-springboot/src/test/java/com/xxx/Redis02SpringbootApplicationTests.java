package com.xxx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.pojo.User;
import com.xxx.utils.RedisUtils02;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushAll();
//        connection.flushDb();

        // redisTempLate 操作不同的数据类型，api和我们的指令是一样的
        // opsForVaLue 操作字符串类似string
        // opsForList 操作List类似List
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog
        redisTemplate.opsForValue().set("username", "xxx");
        System.out.println(redisTemplate.opsForValue().get("username"));
    }


    /**
     * 序列化测试
     */
    @Test
    public void xlh() throws JsonProcessingException {
        User user = new User("姓名", 18);
//        String userJson = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }


    @Test
    public void testByRedisUtils() {
        RedisUtils02.set("username", "xxx");
        System.out.println(RedisUtils02.get("username"));
    }
}
