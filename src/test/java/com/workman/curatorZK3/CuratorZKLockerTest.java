package com.workman.curatorZK3;

import com.SpringBootExampleApplication;
import com.workman.zk2.Locker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootExampleApplication.class)
public class CuratorZKLockerTest {

    @Resource(name = "zkCuratorLocker")
    private Locker locker;

    @Test
    public void testZkLocker() {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                locker.lock("user_1", () -> {
                    try {
                        System.out.println(String.format("user_1 time: %d,threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-" + i).start();
        }

        for (int i = 1000; i < 2000; i++) {
            new Thread(() -> {
                locker.lock("user_2", () -> {
                    try {
                        System.out.println(String.format("user_2 time: %d,threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-" + i).start();
        }
    }
}
