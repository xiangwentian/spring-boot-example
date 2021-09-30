package com.workman.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Redis的列表相当于java的LinkedList，注意它是链表不是数组，list的插入和删除非常快，时间复杂度为O(1)，但索引很慢，时间复杂度为O(n)
 * <p>
 * 当列表弹出最后一个元素之后，该数据结构自动被删除，内存被回收
 */
@Repository
public class ListRedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void rightAdd(String key, List value) {
        Long result = redisTemplate.opsForList().rightPush(key, value);
        System.out.println("add list result--->>>" + result);
    }

    public void leftAdd(String key, String value) {
        Long result = redisTemplate.opsForList().leftPush(key, value);
        System.out.println("add list left add--->>>" + result);
    }

    public String get(String key, Integer index) {
        //获取指定下标的元素，如果下标是-1，则返回最后一个，下标越界则返回null
        List result = (List) redisTemplate.opsForList().index(key, index);
        if (null == result) {
            return null;
        }
        return JSON.toJSONString(result);
    }

    public List getRange(String key, Integer begin, Integer end) {
        List result = redisTemplate.opsForList().range(key, begin, end);
        return result;
    }

    public Object leftPop(String key) {
        Object result = redisTemplate.opsForList().leftPop(key);
        return result;
    }

    public Object rightPop(String key) {
        Object result = redisTemplate.opsForList().rightPop(key);
        return result;
    }

}
