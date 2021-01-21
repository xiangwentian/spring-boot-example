package com.workman.factory;

import lombok.Data;

@Data
public class HelloService {
    private String str;

    public HelloService() {
    }

    public HelloService(String str) {
        this.str = str;
    }

    public String print() {
        return this.str;
    }

    @Override
    public String toString() {
        long threadId = Thread.currentThread().getId();
        return "threadId:" + threadId + ", str:" + str;
    }

}
