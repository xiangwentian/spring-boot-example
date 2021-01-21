package com.workman.Conditional;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class ConditionOnBeanTest extends BaseTest implements ApplicationContextAware {

    public org.springframework.context.ApplicationContext ApplicationContext;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.ApplicationContext = applicationContext;
    }

    @Test
    public void test1(){
        Map<String,Computer> beanOfType = ApplicationContext.getBeansOfType(Computer.class);
        System.out.println(JSON.toJSONString(beanOfType));
    }
}
