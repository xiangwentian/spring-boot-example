package com.workman.designMode.singleton;

public enum SingletonEnum {
    INSTANCE;

    public void m() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("args = " + SingletonEnum.INSTANCE.hashCode());
            }).start();
        }
    }
}
