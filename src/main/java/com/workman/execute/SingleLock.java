package com.workman.execute;

/**
 * DCL 双检锁 double check lock
 */
public class SingleLock {
    private static SingleLock INSTANCE;

    public static SingleLock getInstance() {
        if (INSTANCE == null) {
            synchronized (SingleLock.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingleLock();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingleLock.getInstance().hashCode());
            }).start();
        }
    }
}
