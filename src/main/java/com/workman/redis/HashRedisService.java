package com.workman.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 和java的hashmap一样，数组+链表的形式，它是无序字典，redis的字典只能是字符串，另外他们rehash的方式不一样
 * <p>
 * java的hashmap在字典很大时，rehash是个耗时的操作，需要一次性全部rehash。redis为了高性能，不能堵塞服务，所以采用了渐进式rehash策略
 * <p>
 * 渐进式会在rehash同时，保留新旧两个hash结婚，查询时会同时查询两个hash结构，然后在后续定时任务中以及hash操作指令中，循序渐进地将旧hash的内容
 * 一点点迁移到新的hash结构中。当搬迁完成了，就会使用新的hash结构取而代之
 *
 * 当hash移除最后一个元素之后，该数据结构自动被删除，内存被回收
 *
 * 方法可百度参考文档
 * 提供案例：https://www.cnblogs.com/meijsuger/p/12036281.html
 */
@Repository
public class HashRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void hset(String h,Object hk,Object hv){
        redisTemplate.opsForHash().put(h,hk,hv);
    }

    public Object hgetAll(String h){
        return redisTemplate.opsForHash().entries(h);
    }

    public Boolean hasKey(String key,String hashKey){
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    public Object hget(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public Long hdel(String key,String hashKey){
        return redisTemplate.opsForHash().delete(key,hashKey);
    }
}
