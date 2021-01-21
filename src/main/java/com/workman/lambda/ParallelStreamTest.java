package com.workman.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 并行流
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
        ParallelStreamTest parallelStreamTest = new ParallelStreamTest();
//        try {
//            parallelStreamTest.nomalFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        parallelStreamTest.parallel();
    }

    public void nomalFor() throws InterruptedException {
        List<Apple> appleList = initAppleList(); // 假装数据是从库里查出来的

        Date begin = new Date();
        for (Apple apple : appleList) {
            apple.setPrice(5.0 * apple.getWeight() / 1000);
            Thread.sleep(1000);
        }
        Date end = new Date();
        System.out.println("苹果数量：" + appleList.size() + "个, 耗时：" + (end.getTime() - begin.getTime()) / 1000 + " s");
    }

    public void parallel() {
        List<Apple> appleList = initAppleList(); // 假装数据是从库里查出来的
        Date begin = new Date();
        appleList.parallelStream().forEach(apple -> {
            apple.setPrice(5.0 * apple.getWeight() / 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Date end = new Date();
        System.out.println("苹果数量：" + appleList.size() + "个, 耗时：" + (end.getTime() - begin.getTime()) / 1000 + " s");
    }

    private List<Apple> initAppleList() {
        List<Apple> initList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Apple apple = new Apple();
            apple.setPrice(12);
            apple.setWeight(0.5);
            initList.add(apple);
        }
        return initList;
    }

    @Data
    private class Apple {
        private double weight;
        private double price;
    }
}
