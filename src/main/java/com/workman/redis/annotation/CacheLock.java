package com.workman.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 注解类
 * prefix： 缓存中 key 的前缀
 * expire： 过期时间，此处默认为 5 秒
 * timeUnit： 超时单位，此处默认为秒
 * delimiter： key 的分隔符，将不同参数值分割开来;key 的生成规则是自己定义
 * 通过表达式语法自己得去写解析规则还是比较麻烦的，所以用注解的方式
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {
    /**
     * redis 锁key的前缀
     * @return redis 锁key的前缀
     */
    String prefix() default "";

    /**
     * 过期秒数，默认 5 秒
     * @return 轮询锁的时间
     */
    long expire() default 10;

    /**
     * 超时时间单位
     * @return 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * <p>key的分隔符(默认:)</p>
     * <p>生成的Key：N:SO1008:500</p>
     * @return
     */
    String delimiter() default ":";
}
