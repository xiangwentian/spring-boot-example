package com.workman.designMode.asm;

public class AsmTest {
    int i = 0;

    public void m() {
        int j = 1;
    }

    public static void main(String[] args) {
        AsmTest asm = new AsmTest();
        asm.m();
    }
}
