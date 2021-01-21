package com.workman.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @Autowired
    PostConstructTest postConstructTest;

    @RequestMapping("postConTest")
    public void postConstructTest() {
        System.out.println(postConstructTest.str);
    }
}
