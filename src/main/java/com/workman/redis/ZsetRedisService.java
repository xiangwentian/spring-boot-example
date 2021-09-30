package com.workman.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * zset是redis最为特色的数据结构，也是面试最爱问到的。
 * 它类似于java的SortedSet和HashMap的结合休
 * 一方面它是一个set,保证了内部value的唯一性，另一方面它可以给每个value赋予一个score,代表这个value的排序权重
 * 它的内部实现用的是一种叫做[跳跃列表]的数据结构
 * zset最后一个value被移除后，数据结构自动删除，内存被回收
 * <p>
 * zset可以用来存粉丝列表，value值是粉丝的用户id，score是关注时间，我们可以对粉丝列表按关注时间进行排序
 * zset还可以存储学生的成绩，value是学生的id，score是他的考试成绩，我们可以对成绩按分数进行排序来得到他的名次
 */
@Repository
public class ZsetRedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void zadd(String k, String v, double v1) {
        redisTemplate.opsForZSet().add(k, v, v1);
    }

    public void zadd(String k, Set<ZSetOperations.TypedTuple<String>> v) {
        redisTemplate.opsForZSet().add(k, v);
    }

    /**
     * base score sort set with range
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public Object zRange(String k, long l, long l1) {
        return redisTemplate.opsForZSet().range(k, l, l1);
    }

    /**
     * base score desc sort with range
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public Object zrevrange(String k, long l, long l1) {
        return redisTemplate.opsForZSet().reverseRange(k, l, l1);
    }

    /**
     * count zset size
     *
     * @param k
     * @return
     */
    public Long zcard(String k) {
        return redisTemplate.opsForZSet().zCard(k);
    }

    /**
     * get value's score
     *
     * @param k
     * @param o
     * @return
     */
    public Double zscore(String k, Object o) {
        return redisTemplate.opsForZSet().score(k, o);
    }

    /**
     * get sort 获取排名
     *
     * @param k
     * @param o
     * @return
     */
    public Long zrank(String k, Object o) {
        return redisTemplate.opsForZSet().rank(k, o);
    }

    /**
     * 按分值排名
     *
     * @param k
     * @param startScore 开始分值
     * @param endScore   结束分值
     * @return
     */
    public Object zrangebyscore(String k, Double startScore, Double endScore) {
        return redisTemplate.opsForZSet().rangeByScore(k, startScore, endScore);
    }

    /**
     * 按分值排序 并返回 分值
     *
     * @param k
     * @param startScore
     * @param endScore
     * @return
     */
    public Object zrangebyscoreWithScore(String k, Double startScore, Double endScore) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(k, startScore, endScore);
    }

    /**
     * 删除value
     *
     * @param k
     * @param o
     * @return
     */
    public Long zrem(String k, Object o) {
        return redisTemplate.opsForZSet().remove(k, o);
    }
}
