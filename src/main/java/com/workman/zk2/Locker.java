package com.workman.zk2;

/**
 * 定义一个Locker接口
 */
public interface Locker {
    void lock(String key,Runnable command);
}
