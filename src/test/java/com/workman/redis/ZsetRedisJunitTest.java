package com.workman.redis;

import com.alibaba.fastjson.JSON;
import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZsetRedisJunitTest extends BaseTestRunner {

    @Autowired
    private ZsetRedisService zsetRedisService;

    @Test
    public void zadd() {
        String k = "book-zset";
        String v = "think in java 3";
        double v1 = 9.0;
        zsetRedisService.zadd(k, v, v1);
    }

    @Test
    public void zaddSet() {
        String k = "book-zset";
        String v = "think in java";
        double v1 = 9.0;
        DefaultTypedTuple<String> tuple1 = new DefaultTypedTuple<>(v, v1);

        v = "java concurrency";
        v1 = 8.9;
        DefaultTypedTuple<String> tuple2 = new DefaultTypedTuple<>(v, v1);

        v = "java cookbook";
        v1 = 8.6;
        DefaultTypedTuple<String> tuple3 = new DefaultTypedTuple<>(v, v1);

        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>(Arrays.asList(tuple1, tuple2, tuple3));
//        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>(Arrays.asList(tuple1));
        zsetRedisService.zadd(k, set);
    }

    /**
     * 根据score排序列出，参数区间为排名范围
     */
    @Test
    public void zrang() {
        String k = "book-zset";
        long l = 0;
        long l1 = -1;
        Object obj = zsetRedisService.zRange(k, l, l1);
        if (null == obj) {
            System.out.println("zrang --->>> is null");
        }
        System.out.println("zrang --->>>" + JSON.toJSONString(obj));
    }

    /**
     * 根据score逆序列出，参数区间为排名范围
     */
    @Test
    public void zrevrange() {
        String k = "book-zset";
        long l = 0;
        long l1 = -1;
        Object obj = zsetRedisService.zrevrange(k, l, l1);
        if (null == obj) {
            System.out.println("zrevrange --->>> is null");
        }
        System.out.println("zrevrange --->>>" + JSON.toJSONString(obj));
    }

    @Test
    public void zcard() {
        String k = "book-zset";
        Long count = zsetRedisService.zcard(k);
        System.out.println("count zset--->>>" + count);
    }

    /**
     * 获取value的score
     */
    @Test
    public void zscore() {
        String k = "book-zset";
        Object obj = "think in java 3";
        Double d = zsetRedisService.zscore(k, obj);
        System.out.println("zscore--->>>" + d);
    }

    /**
     * 按正序排名，获取value的排名
     */
    @Test
    public void zrank() {
        String k = "book-zset";
        Object obj = "think in java 3";
        Long l = zsetRedisService.zrank(k, obj);
        System.out.println("zrank--->>>" + l);
    }

    /**
     * 按评分获取区间
     */
    @Test
    public void zrangebyscore() {
        String k = "book-zset";
        Double startScore = 0d;
        Double endScore = 8.91;
        Object obj = zsetRedisService.zrangebyscore(k, startScore, endScore);
        if (null == obj) {
            System.out.println("zrangebyscore--->>> is null");
        }
        System.out.println("zrangebyscore--->>> " + JSON.toJSONString(obj));
    }

    @Test
    public void zrangebyscoreWithScore() {
        String k = "book-zset";
        Double startScore = 0d;
        Double endScore = 8.91;
        Object obj = zsetRedisService.zrangebyscoreWithScore(k, startScore, endScore);
        if (null == obj) {
            System.out.println("zrangebyscoreWithScore--->>> is null");
        }
        System.out.println("zrangebyscoreWithScore--->>> " + JSON.toJSONString(obj));
    }

    /**
     * zset删除obj
     */
    @Test
    public void zrem() {
        String k = "book-zset";
        Object obj = "think in java 3";
        Long l = zsetRedisService.zrem(k, obj);
        System.out.println("zrem --->>>" + l);
    }
}
