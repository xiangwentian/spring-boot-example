package com.workman.string;


import io.micrometer.core.instrument.util.StringUtils;

/**
 * String的内存和intern()方法
 * https://www.cnblogs.com/wangshen31/p/10404353.html
 */
public class StringTest {
    public static String getGroupedName(String serviceName, String groupName) {
        if (StringUtils.isBlank(serviceName)) {
            throw new IllegalArgumentException("Param 'serviceName' is illegal, serviceName is blank");
        } else {
            String resultGroupedName = groupName + "@@" + serviceName;
            return resultGroupedName.intern();
        }
    }

    public static String getGroupedName() {
        String groupName = "default_group";
        String serviceName = "nacos";
        String resultGroupedName = groupName + "@@" + serviceName;
        return resultGroupedName.intern();
    }

    public static void constantTest() {
        //return true
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        //return false : constant is have this "java",jvm already load
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

    public static void main(String[] args) {
        String strIntern = getGroupedName("nacos", "default_group");
        System.out.println("strIntern = " + strIntern);
        String strIntern2 = getGroupedName();
        System.out.println("strIntern2 = " + strIntern2);
//        System.out.println("==：" + strIntern == strIntern2);
//        System.out.println("equals：" + strIntern.equals(strIntern2));
        System.out.println("strIntern.intern == " + (strIntern == "default_group@@nacos"));
        System.out.println("strIntern2.intern == " + (strIntern2 == "default_group@@nacos"));

//        constantTest();
    }
}
