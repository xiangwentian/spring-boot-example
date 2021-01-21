package com.workman.factory;

public class FactoryTest {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            HelloService helloService = initFactory();
            System.out.println(helloService.toString());
        }
    }

    public static HelloService initFactory() {
        String str = "hello world";
        HelloService helloService = createHelloService(str);
        return helloService;
    }

    public static HelloService createHelloService(String str) {
        return ServiceFactory.createHelloService(str);
    }

}
