package com.workman.redissonLimite;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redisson实现限流操作
 */
@Slf4j
@Component
public class RedissonRate {
    private final RedissonClient redisClient;
    private final String key = "msgRateLimiter:" + "test";
    private final int limiter = 100;

    @Autowired
    public RedissonRate(RedissonClient redisClient) {
        this.redisClient = redisClient;
    }

    /**
     * 服务启动的时候，先清一下redis，防止count 出错
     */
    public void reload() {
        RMapCache<String, Integer> msgRateLimit = redisClient.getMapCache(key, IntegerCodec.INSTANCE);
        if (msgRateLimit.containsKey(key)) {
            msgRateLimit.delete();
        }
    }

    /**
     * 该方法可以结合mq，结果是true的话就ack，false就reject
     */
    public boolean handleMessage() {
        //分布式下的限流
        RMapCache<String, Integer> msgRateLimit = redisClient.getMapCache(key, IntegerCodec.INSTANCE);
        Integer count;
        try {
            msgRateLimit.putIfAbsent(key, 0, 1L, TimeUnit.SECONDS);
            count = msgRateLimit.addAndGet(key, 1);
            log.info("get redis count:{}", count);
            if (count < limiter) {
                //此处是你要执行的代码
                return true;
            }
            log.warn("超过限流:{}", count);
        } catch (Exception e) {
            log.error("err", e);
        }
        return false;
    }
}
