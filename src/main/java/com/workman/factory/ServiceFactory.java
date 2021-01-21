package com.workman.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ServiceFactory {
    public ServiceFactory() {
    }

    public static HelloService createHelloService(String str) {
        try {
            Class<?> driverImplService = Class.forName("com.source.workman.factory.HelloService");
            Constructor constructor = driverImplService.getConstructor(String.class);
            HelloService helloService = (HelloService) constructor.newInstance(str);
            return helloService;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
