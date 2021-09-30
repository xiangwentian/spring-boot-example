package com.workman.redis;

import com.workman.BaseTestRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * int值，存储、自增、查询
 */
public class IntegerRedisJunitTest extends BaseTestRunner {
    @Autowired
    private IntegerRedisService integerRedisService;

    @Test
    public void add() {
        String key = "workman:age";
        Integer value = 30;
        integerRedisService.add(key, value);
        Object getAge = integerRedisService.get(key);
        System.out.println("Integer value,get age--->" + getAge);
    }

    @Test
    public void incrby() {
        String key = "workman:age";
        Object getAge = integerRedisService.get(key);
        System.out.println("Integer value,get age--->" + getAge);
        Integer afterincrby = integerRedisService.incrby(key);//自增后会返回原自增加前的数值
        getAge = integerRedisService.get(key);
        System.out.println("Integer value,get add incry age--->" + getAge);
    }

    @Test
    public void get() {
        String key = "workman:age";
        int getAge = integerRedisService.get(key);
        System.out.println("get int age: " + getAge);
    }
}
