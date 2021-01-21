package com.workman.annotation;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class PostConstructTest {

    static String str = "hello";

    public PostConstructTest() {
        System.out.println("init constructor");
    }

    public PostConstructTest(String str) {
        this.str = str;
    }

    @PostConstruct
    public void start() {
        System.out.println("after constructor invoke," + this.str);
    }

    public static void main(String[] args) {
        PostConstructTest postConstructTest = new PostConstructTest("hello");
    }

}
