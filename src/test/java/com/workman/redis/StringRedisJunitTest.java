package com.workman.redis;

import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StringRedisJunitTest extends BaseTestRunner {

    @Autowired
    private StringRedisService redisService;

    @Test
    public void set() {
        redisService.add("user", "workman-new", 1L);
        get();
    }

    @Test
    public void setExpir() {
        String key = "user-new";
        redisService.expir(key);
    }

    @Test
    public void get() {
        String value = redisService.get("user");
        System.out.println("invoke get ,value--->>>" + value);
    }

    @Test
    public void setIfAbsent() {
        redisService.setIfAbsent("user-new", "hello-new");
        String userNew = redisService.get("user-new");
        System.out.println("userNew--->>>" + userNew);
    }

}
