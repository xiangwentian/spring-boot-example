package com.workman.utils;

import com.workman.config.RedissonConfig;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedissonManager {
    private Config config;
    private RedissonClient client;
    private final static String LOCK_NAME = "RM:LOCK";

    @Autowired
    private RedissonConfig redissonConfig;

    @PostConstruct
    public void init() {
        log.info("===============redisson start");
        if (null == client) {
            log.info("===========redisson create");
            config = redissonConfig.getConfig();
            client = Redisson.create(config);
            log.info("===========redisson create fin");
        }
    }

    public RedissonClient getClient() {
        config = redissonConfig.getConfig();
        RedissonClient c = Redisson.create(config);
        return c;
    }

    /**
     * 获取锁
     *
     * @param key
     * @param timeout
     * @return
     */
    public RLock getLock(String key, Long timeout) {
        log.info("========locking");
        RLock lock = client.getLock(key);
        if (-1 == timeout) {
            lock.lock();
        } else {
            lock.lock(timeout, TimeUnit.SECONDS);
        }
        log.info("========locked");
        return lock;
    }

    public boolean releaseLock(String key) {
        RLock lock = client.getLock(key);
        if (null != lock) {
            //log.info("========hold count:{}", lock.getHoldCount());
            lock.unlock();
            return true;
        } else {
            log.info("========没有找到锁");
            return false;
        }
    }

    public boolean tryLock(String key) {
        RLock lock = client.getLock(key);
        try {
            return lock.tryLock(0, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("tryLock method error,", e);
            return false;
        }
    }
}
