package com.workman.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 相当于java的HashSet，内部的键值对是无序唯一的，内存实现相当于一个特殊的字典，字典中所有的value都是一个值NULL
 * 当集合中最后一个元素移除之后，数据结构自动删除，内存被回收
 */
@Repository
public class SetRedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void sadd(String k, String vs) {
        redisTemplate.opsForSet().add(k, vs);
    }

    public boolean sismember(String k, Object vs) {
        return redisTemplate.opsForSet().isMember(k, vs);
    }

    public Object smembers(String k) {
        return redisTemplate.opsForSet().members(k);
    }

    public Long scans(String k) {
        return redisTemplate.opsForSet().size(k);
    }

    public Object spop(String k) {
        return redisTemplate.opsForSet().pop(k);
    }
}
