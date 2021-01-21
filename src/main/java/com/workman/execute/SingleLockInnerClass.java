package com.workman.execute;

/**
 * 静态内部类方式实现
 */
public class SingleLockInnerClass {
    private SingleLockInnerClass() {
    }

    public static SingleLockInnerClass getInstance() {
        return SingleTonHoler.INSTANCE;
    }

    private static class SingleTonHoler {
        private static SingleLockInnerClass INSTANCE = new SingleLockInnerClass();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingleLockInnerClass.getInstance().hashCode());
            }).start();
        }
    }
}
