package com;

import com.workman.kafka.producer.UserLogProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class SpringBootExampleApplication {

    @Autowired
    private UserLogProducer kafkaSender;

    @PostConstruct
    public void init() {

//        new Thread(() -> {
//            for (int i = 0; i < 100000; i++) {
//                kafkaSender.sendLog(String.valueOf(i));
//            }
//        }, "thread1").start();
//
//        new Thread(() -> {
//            for (int i = 100000; i < 200000; i++) {
//                kafkaSender.sendLog2(String.valueOf(i));
//            }
//        }, "thread2").start();

        log.info("run done");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExampleApplication.class, args);
    }

}
