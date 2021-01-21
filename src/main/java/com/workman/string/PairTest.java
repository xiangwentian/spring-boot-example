package com.workman.string;

import com.alibaba.nacos.common.utils.Pair;
import lombok.Data;

/**
 * 返回多个参数
 */
public class PairTest {
    public static A a;
    public static B b;

    public static void test() {
        //Pair<A, B> pair = new Pair<A, B>("value1", "value2");
//        Pair.with("value1", "value2");
//        System.out.println("a:" + a + " ,b:" + b);
        Pair<String, Integer> pair = Pair.with("hello", Integer.valueOf(23));
        System.out.println("1:" + pair.getFirst() + " ,2:" + pair.getSecond());
    }

    public static void main(String[] args) {
        test();
    }

}

@Data
class A {
    String a;
}

@Data
class B {
    String b;
}