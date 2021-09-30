package com.workman.redis;

import com.alibaba.fastjson.JSON;
import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HashRedisJunitTest extends BaseTestRunner {

    @Autowired
    private HashRedisService hashRedisService;

    String key = "book";
    String hk = "java";

    @Test
    public void hset() {
        String hk = "java";
        String hv = "think in java";
        hashRedisService.hset(key, hk, hv);
    }

    @Test
    public void hget() {

        Object obj = hashRedisService.hget(key, hk);
        if (null == obj) {
            System.out.println("hget is null--->>>");
        }
        System.out.println("hget --->>>" + JSON.toJSONString(obj));
    }

    @Test
    public void hdel() {
        String key = "golang";
        Long del = hashRedisService.hdel(key, hk);
        System.out.println("hdel--->>>" + del);
    }

    @Test
    public void hgetAll() {
        Object object = hashRedisService.hgetAll(key);
        if (null == object) {
            System.out.println("hgetAll is null --->>>");
            return;
        }
        System.out.println("hgetAll invoke result --->>>" + JSON.toJSONString(object));
    }

    @Test
    public void hasKey() {
        String hashKey = "java";
        boolean has = hashRedisService.hasKey(key, hashKey);
        System.out.println("hasKey--->>>" + has);
    }


}
