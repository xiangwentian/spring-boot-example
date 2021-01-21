package com.workman.designMode.proxy;

public class Mac implements Computer {
    @Override
    public final String type() {
        System.out.println("mac book");
        return "mac book";
    }
}
