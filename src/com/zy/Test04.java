package com.zy;

public class Test04 {
    static {
        System.out.println("test04");
    }
    public static void main(String[] args) {
        System.out.println(Child4.str);
        /*
        * [Loaded com.zy.Test04 from file:/D:/workspace/IdeaProjects/jvm_lecture/out/production/jvm_lecture/]
        [Loaded sun.launcher.LauncherHelper$FXHelper from D:\jdk1.8.0_131\jre\lib\rt.jar]
        [Loaded java.lang.Void from D:\jdk1.8.0_131\jre\lib\rt.jar]
        test04
        [Loaded com.zy.Partent4 from file:/D:/workspace/IdeaProjects/jvm_lecture/out/production/jvm_lecture/]
        [Loaded com.zy.Child4 from file:/D:/workspace/IdeaProjects/jvm_lecture/out/production/jvm_lecture/]
        Parent4
        child4
        aaaa
        *
        *
        * */
    }
}
class Partent4{
    public static  String str1 = "aaaa";
    static {
        System.out.println("Parent4");
    }
}
class Child4 extends Partent4{
    public static  String str = "aaaa";
    static {
        System.out.println("child4");
    }
}
