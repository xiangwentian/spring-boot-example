package com.workman.Event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class DemoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
