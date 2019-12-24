package com.workman;

import com.workman.utils.RedissonManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Resource
    private RedissonManager manager;

    //@Test
    public void testRedis() {
        Long invokeTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(3);
        for (int i = 0; i < 500; i++) {
            String uuid = UUID.randomUUID().toString();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("thread in ...{}", Thread.currentThread().getName());
                    boolean result = manager.tryLock("lock:timer");
                    log.info("{},开启锁,{}", Thread.currentThread().getName(), result);
                    if (result) {
                        log.info("没有线程在操作,{},正常执行", Thread.currentThread().getName());
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        boolean result2 = manager.releaseLock("lock:timer");
                        log.info("{}释放锁{}", Thread.currentThread().getName(), result2);
                    } else {
                        log.info("已有线程在操作{},退出", Thread.currentThread().getName());
                    }
                }
            });
        }

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String uuid = UUID.randomUUID().toString();
        boolean result = manager.tryLock("lock:timer");
        log.info("主线程获取锁:{}", result);
        if (result) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = manager.releaseLock("lock:timer");
            log.info("主线程释放锁:{}", flag);
            log.info("执行时间：{}", (System.currentTimeMillis() - invokeTime));
        }
    }
}
