package com.workman.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Repository;

@Repository
public class IntegerRedisService {
    @Autowired
    private RedisTemplate redisTemplate;


    public void add(String key, Integer value) {
//        redisTemplate.opsForValue().set(key, value);
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key,redisTemplate.getConnectionFactory());
        redisAtomicInteger.set(value);
    }

    public int get(String key) {
        //return redisTemplate.opsForValue().get(key);
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key,redisTemplate.getConnectionFactory());
        return redisAtomicInteger.get();
    }

    public Integer incrby(String key) {
        //redisTemplate.opsForValue().increment(key);
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(key,redisTemplate.getConnectionFactory());
        return redisAtomicInteger.getAndIncrement();
    }

    public void incrby(String key, Double incrVal) {
        redisTemplate.opsForValue().increment(key, incrVal);
    }

}
