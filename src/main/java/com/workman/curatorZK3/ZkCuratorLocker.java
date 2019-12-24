package com.workman.curatorZK3;

import com.workman.zk2.Locker;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component("zkCuratorLocker")
public class ZkCuratorLocker implements Locker {

    public static final String connAddr = "192.168.118.130:2181";
    public static final int timeout = 6000;
    public static final String LOCK_ROOT = "/locker";

    private CuratorFramework cf;

    @PostConstruct
    public void init() {
        this.cf = CuratorFrameworkFactory.builder().connectString(connAddr).sessionTimeoutMs(timeout).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        cf.start();
    }

    @Override
    public void lock(String key, Runnable command) {
        log.info("{},进入轮子curator lock method,thread->{}", key, Thread.currentThread().getName());
        String path = LOCK_ROOT + "/" + key;
        InterProcessLock lock = new InterProcessMutex(cf, path);
        try {
            lock.acquire();
            command.run();
        } catch (Exception e) {
            log.error("get lock error", e);
            throw new RuntimeException("get lock error");
        } finally {
            try {
                lock.release();
            } catch (Exception e) {
                log.error("release log error", e);
                throw new RuntimeException("release log error");
            }
        }
    }
}
