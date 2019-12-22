package com.workman;

import com.workman.redissonLimite.RedissonRate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonLimiteTest {

    @Autowired
    private RedissonRate redissonRate;

    @Test
    public void TestLimit() {
        redissonRate.reload();
        for (int i = 0; i < 150; i++) {
            boolean result = redissonRate.handleMessage();
            log.info("redisson test,current num:{},result:{}", i, result);
        }
    }
}
