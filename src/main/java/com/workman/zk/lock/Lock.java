package com.workman.zk.lock;

public interface Lock {
    //获取锁资源
    public void getLock();
    //释放锁资源
    public void unlock();
}
