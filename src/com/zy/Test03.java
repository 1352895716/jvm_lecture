package com.zy;

public class Test03 {
    public static void main(String[] args) {
        Parent3 p = new Parent3();
        System.out.println(p.getClass());//class com.zy.Parent3
        Parent3[] ps = new Parent3[1];
        System.out.println(ps.getClass());//class [Lcom.zy.Parent3;
        Parent3[] ps2 = new Parent3[2];
        System.out.println(ps2.getClass());//class [Lcom.zy.Parent3;
    }
}
class Parent3{
    static {
        System.out.println("aaaaaaaaaaa");
    }
}