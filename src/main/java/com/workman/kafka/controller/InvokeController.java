package com.workman.kafka.controller;

import com.workman.kafka.producer.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class InvokeController {

    @Autowired
    private UserLogProducer kafkaSender;

    @RequestMapping("heartcheck")
    public String test() {
        return "invoke worked";
    }

    @RequestMapping("run")
    public String runKafka() {

        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                kafkaSender.sendLog(String.valueOf(i));
            }
        }, "thread1").start();

        new Thread(() -> {
            for (int i = 1000000; i < 2000000; i++) {
                kafkaSender.sendLog2(String.valueOf(i));
            }
        }, "thread2").start();

        return "执行成功";
    }
}
