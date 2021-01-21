package com.workman.designMode.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * 当方法是final修饰时，不能使用cglib生成代理类，jdk是可以的
 * asm是可以代理的，什么也挡不住
 * cglib底层用的也是asm
 */
public class CglibProxyMain {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Mac.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Mac mac = (Mac) enhancer.create();
        mac.type();
    }
}

class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, net.sf.cglib.proxy.MethodProxy methodProxy) throws Throwable {
        System.out.println(o.getClass().getSuperclass().getName());
        System.out.println("before");
        Object result = null;
        result = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return result;
    }
}
