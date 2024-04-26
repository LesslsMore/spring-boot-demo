package com.lesslsmore.bili.db;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class redis_template_test {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void testString() {
        redisTemplate.opsForValue().set("key", "lim");

        Object key = redisTemplate.opsForValue().get("key");
        System.out.println(key);
    }

    @Test
    void test() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("key1", "value1_lim");
        String s = ops.get("key1");
        System.out.println(s);
//        ops.setIfAbsent();
//        String string = UUID.randomUUID().toString();
//        stringRedisTemplate.expire();
//        stringRedisTemplate.delete();
        String jsonString = JSON.toJSONString(s);
        boolean empty = StringUtils.isEmpty(s);
    }
}