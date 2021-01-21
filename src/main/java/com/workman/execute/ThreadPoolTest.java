package com.workman.execute;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class ThreadPoolTest {

    private static ExecutorService serviceUpdateExecutor
            = Executors.newFixedThreadPool(2, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("thead test name");
//            t.setDaemon(true);
            return t;
        }
    });

    private class UpdatedServiceProcessor implements Runnable {
        private int i;

        public UpdatedServiceProcessor(int i) {
            this.i = i;
        }

        @Override
        public void run() {
//            log.info("i:{},threadid:{},threadname:{}", i, Thread.currentThread().getId(), Thread.currentThread().getName());
            hello(i);
//            System.out.println("test" + i + " ,thread id=" + Thread.currentThread().getId());
        }
    }

    public static void hello(int i) {
        log.info("hello {}", i);
    }

    public static void main(String[] args) {
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        for (int i = 0; i < 50; i++) {
//            log.info("into test method ,for print:{}", i);
            serviceUpdateExecutor.execute(threadPoolTest.new UpdatedServiceProcessor(i));
        }
        serviceUpdateExecutor.shutdown();
    }
}
