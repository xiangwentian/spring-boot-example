package com.workman.redis;

import com.alibaba.fastjson.JSON;
import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetRedisJunitTest extends BaseTestRunner {
    @Autowired
    private SetRedisService setRedisService;

    String key = "books";

    @Test
    public void sadd() {
        String vs = "python";
        setRedisService.sadd(key, vs);
    }

    @Test
    public void sismember() {
        String vs = "python";
        boolean has = setRedisService.sismember(key, vs);
        System.out.println("sismember --->>>" + has);
    }

    @Test
    public void smembers() {
        Object obj = setRedisService.smembers(key);
        if (null == obj) {
            System.out.println("smembers--->>> is null");
        }
        System.out.println("smembers--->>>" + JSON.toJSONString(obj));
    }

    @Test
    public void scans() {
        Long count = setRedisService.scans(key);
        System.out.println("scans--->>>" + count);
    }

    @Test
    public void spop() {
        Object obj = setRedisService.spop(key);
        if (null == obj) {
            System.out.println("spop--->>> is null");
        }
        System.out.println("spop--->>>" + JSON.toJSONString(obj));
    }
}
