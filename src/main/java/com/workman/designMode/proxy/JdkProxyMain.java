package com.workman.designMode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyMain {
    public static void main(String[] args) {
        Mac mac = new Mac();

//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Computer computer = (Computer) Proxy.newProxyInstance(Mac.class.getClassLoader(), new Class[]{Computer.class}, new MacProxy(mac));
        computer.type();
    }

}

class MacProxy implements InvocationHandler {

    Computer computer;

    public MacProxy(Computer computer) {
        this.computer = computer;
    }

    public void before() {
        System.out.println("method start ...");
    }

    public void after() {
        System.out.println("method after ...");
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(computer, args);
        after();
        return o;
    }
}