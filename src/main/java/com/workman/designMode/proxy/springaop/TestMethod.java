package com.workman.designMode.proxy.springaop;

import org.springframework.stereotype.Component;

@Component
public class TestMethod {

    @Workman
    public void hello() {
        System.out.println("hello world");
    }

    @Workman
    public String hello2(String str) {
        System.out.println("hello " + str);
        return "hello " + str;
    }
}
