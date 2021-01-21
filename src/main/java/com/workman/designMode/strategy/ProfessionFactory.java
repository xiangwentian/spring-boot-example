package com.workman.designMode.strategy;

import java.util.HashMap;
import java.util.Map;

public class ProfessionFactory {
    private static Map<String, Profession> map = new HashMap<>();

    private static final Profession DEFAULT_PROFESSION = new DefaultProfession();

    static {
        map.put("student", new Student());
        map.put("teacher", new Teacher());
        map.put("programmer", new Programmer());
    }

    public static Profession getProfession(String s) {
        Profession profession = map.get(s);
        return profession == null ? DEFAULT_PROFESSION : profession;
    }

    static class DefaultProfession implements Profession {
        @Override
        public void outPut() {
            System.out.println("未知职业");
        }
    }
}
