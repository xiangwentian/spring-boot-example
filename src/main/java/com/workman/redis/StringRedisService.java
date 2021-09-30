package com.workman.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * 字符串是Redis最简单的数据结构，唯一key值获取相应的value数据，不同类型的数据结构的差异在于value的结构不一样
 */
@Slf4j
@Repository
public class StringRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 直接存入
     *
     * @param key
     * @param value
     * @param time
     */
    public void add(String key, String value, Long time) {
        log.info("redis string add,key={},value={},time={}", key, value, time);
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    /**
     * 与add方法 带时间效果是一样的，即等于 set+expire
     *
     * @param key
     */
    public void expir(String key) {
        stringRedisTemplate.opsForValue().set(key, "workman-new");
        stringRedisTemplate.expire(key, 20, TimeUnit.SECONDS);
    }

    /**
     * 获取key对应的value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String source = stringRedisTemplate.opsForValue().get(key);
        log.info("redis string get,key={},return val={}", key, source);
        return source;
    }

    /**
     * 当key不存在时才存储
     *
     * @param key
     * @param value
     */
    public void setIfAbsent(String key, String value) {
        stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

}
