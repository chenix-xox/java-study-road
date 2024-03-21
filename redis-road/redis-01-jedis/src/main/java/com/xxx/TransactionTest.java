package com.xxx;

import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 事务测试
 *
 * @author Chenix
 * @create 2024-03-21 22:13
 */
public class TransactionTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.27.199.182", 6379);
        jedis.auth("20011215");

        // 添加测试数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "xxx");
        jsonObject.put("password", "pwd");
        String result = jsonObject.toString();

        // 清空数据库
        jedis.flushDB();

        // 开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("user1", result);
            multi.set("user2", result);
            int i = 1 / 0;
            multi.exec();
        } catch (Exception e) {
            // 执行失败，关闭事务
            multi.discard();
            e.printStackTrace();
        } finally {
            // 执行成功，打印值
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));

            // 关闭redis连接
            jedis.close();
        }
    }
}
