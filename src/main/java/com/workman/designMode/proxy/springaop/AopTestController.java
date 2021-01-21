package com.workman.designMode.proxy.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTestController {

    @Autowired
    TestMethod testMethod;

    @RequestMapping("aop")
    public String aopTest() {
        testMethod.hello();
        return "SUCCESS";
    }

    @RequestMapping("aop2")
    public String aopTest2(String str) {
        return testMethod.hello2(str);
    }
}
