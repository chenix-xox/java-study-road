package com.xxx;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * 部分命令测试
 * @author Chenix
 * @create 2024-03-21 21:03
 */
public class TestPing {
    public static void main(String[] args) {
        // 1. 新建Jedis对象
        Jedis jedis = new Jedis("120.27.199.182", 6379);
        jedis.auth("20011215");
        System.out.println(jedis.ping());
        System.out.println("清空数据：" + jedis.flushDB());
        System.out.println("判断某个键是否存在：" + jedis.exists("username"));
        System.out.println("新增<'username','xxx'>的键值对：" + jedis.set("username", "xxx"));
        System.out.println("新增<'password','password'>的键值对：" + jedis.set("password", "pwd"));
        System.out.print("系统中所有的键如下：");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("password:" + jedis.del("password"));
        System.out.println("判断键password,是否存在：" + jedis.exists("password"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
        System.out.println("随机返回key空间的一个：" + jedis.randomKey());
        System.out.println("key:" + jedis.rename("username", "name"));
        System.out.println("取出改后的name:" + jedis.get("name"));
        System.out.println("按索引查询：" + jedis.select(0));
        System.out.println("查看当前数据库中key的数目：" + jedis.dbSize());
        System.out.println("查看当前数据库中所有的key：" + jedis.keys("*"));
        System.out.println("删除当前选择数据库中的所有key:" + jedis.flushDB());
        System.out.println("返回当前数据库中key的数目：" + jedis.dbSize());
        System.out.println("删除所有数据库中的所有key:" + jedis.flushAll());

        jedis.close();
    }
}
