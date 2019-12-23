package com.workman.zk.service;

import com.workman.zk.lock.Lock;
import com.workman.zk.lock.ZookeeperDistributeLock;
import com.workman.zk.model.OrderNumberGenerate;

public class OrderService implements Runnable{

    OrderNumberGenerate generate = new OrderNumberGenerate();
    //使用自己创建的lock锁
    private Lock lock = new ZookeeperDistributeLock();
    @Override
    public void run() {
        try {
            //获取锁资源
            lock.getLock();
            getNumber();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁资源
            lock.unlock();
        }
    }
    //生成订单
    public void getNumber(){
        String number = generate.getNumber();
        System.out.println(Thread.currentThread().getName()+",生成订单ID："+number);
    }

    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
        for (int i=0;i<10;i++) {
            new Thread(new OrderService()).start();
        }
    }
}

