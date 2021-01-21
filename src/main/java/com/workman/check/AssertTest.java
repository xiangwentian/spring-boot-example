package com.workman.check;


import org.junit.Assert;

public class AssertTest {
    public static void main(String[] args) {
//        String source = null;
//        Assert.notNull(source, "Source must not be null");

        AssertTest assertTest = new AssertTest();
//        assertTest.test(9);

//        assertTest.testNull(null);

        assertTest.assertEqualsTest();
    }

    public void test(int age) {
        assert age >= 0 : "不能输入这种不合理的年龄啊";
        System.out.println("继续执行，输入的年龄是：" + age);
    }

    public void assertEqualsTest() {
        Assert.assertEquals("1", "2");
        System.out.println("assert equals true");
    }

//    public void testNull(String name){
//        Assert.notNull(name, "Source must not be null");
//    }
}
