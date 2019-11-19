package com.zy;

public class Test05 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class clazz = loader.loadClass("com.zy.CL");//只会加载不会初始化
        System.out.println(clazz);
        System.out.println("--------------------------");
        clazz = Class.forName("com.zy.CL");//反射会导致类的初始化
        System.out.println(clazz);
    }


}
class CL{
    static {
        System.out.println("CL");
    }
}




