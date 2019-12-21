package com.workman.redis.interceptor;

import com.workman.redis.annotation.CacheLock;
import com.workman.redis.common.CacheKeyGenerator;
import com.workman.redis.common.LockKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Configuration
public class LockMethodInterceptor {

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private final StringRedisTemplate lockRedisTemplate;
    private final CacheKeyGenerator cacheKeyGenerator;

    @Autowired
    public LockMethodInterceptor(StringRedisTemplate lockRedisTemplate, CacheKeyGenerator cacheKeyGenerator) {
        this.lockRedisTemplate = lockRedisTemplate;
        this.cacheKeyGenerator = cacheKeyGenerator;
    }

    @Around("execution(public * * (..)) && @annotation(com.workman.redis.annotation.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        if (StringUtils.isEmpty(lock.prefix())) {
            throw new RuntimeException("lock key can't be null ...");
        }
        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        try {
            /** key不存在时才能设置成功 */
            final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "");
            if (success) {
                lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
            } else {
                /** 应该抛出一个自定义的 CacheLockException 异常 */
                throw new RuntimeException("请勿重复请求");
            }
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException("系统异常");
            }
        } finally {
            /** 如果演示的话需要注释该代码，实际应该放开 */
//            lockRedisTemplate.delete(lockKey);
        }
    }


    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }

}
