package com.workman.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventRunTest {

    @Autowired
    DemoPublisher demoPublisher;

//    @Test
    @RequestMapping("run")
    public void runEvent() {
        demoPublisher.publish("hello world");
    }
}
