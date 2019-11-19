package com.zy;

import java.util.UUID;

/*
    助记符：
    idc表示将int,float,或是String类型的常量值从常量池中推送至栈顶
    bipush表示将单字节（-128-127）的常量推送至栈顶
    sipush表示将一个短整形的常量值（-32768-32767）推送至栈顶
    iconst_1表示将int类型1推送至栈顶（iconst_1 - iconst_5）

*/
public class Test02 {
    public static void main(String[] args) {
        System.out.println(Parent2.str);
    }
}
class Parent2{
    public final static String str = UUID.randomUUID().toString();

    //public  static String str = "hello world";
    //带final的输出结果：hello world
    /*不带：
    Parent2 static block2
            hello world */

    //分析产生以上结果的原因
    //常量在编译阶段会存入到调用这个常量的方法所在类的常量池中，
    //本质上，调用类并没有直接饮用到定义常量的类，因此并不会触发定义常量类的初始化
    //注意：这里指的是将常量存放到了Test02的常量池中，之后Test02就与Parent2没有任何关系了
    //甚至，我们可以将Parent2的class文件删除
    static {
        System.out.println("Parent2 static block2");
    }
}
