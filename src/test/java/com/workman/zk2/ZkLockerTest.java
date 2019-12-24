package com.workman.zk2;

import com.SpringBootExampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootExampleApplication.class)
public class ZkLockerTest {

    //@Qualifier("zKLocker")
    //@Autowired
    @Resource(name = "zKLocker")
    private Locker locker;

    //@Test
    public void testZkLocker() throws IOException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                locker.lock("user_1", () -> {
                    try {
                        System.out.println(String.format("user_1 time: %d, threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-" + i).start();
        }
        for (int i = 100; i < 200; i++) {
            new Thread(() -> {
                locker.lock("user_2", () -> {
                    try {
                        System.out.println(String.format("user_2 time: %d, threadName: %s", System.currentTimeMillis(), Thread.currentThread().getName()));
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }, "Thread-" + i).start();
        }

        System.in.read();
    }
}