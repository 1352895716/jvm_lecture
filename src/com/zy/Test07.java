package com.zy;

/**
 * @ClassName: Test07
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/12 15:48
 * @Version 1.0
 **/
public class Test07 {
    public static void main(String[] args) {
        String[] strings = new String[3];
        System.out.println(strings.getClass().getClassLoader());//null,此处的null对应的是跟类加载器
        System.out.println("----------------------------");
        Test07[] test07s = new Test07[3];
        System.out.println(test07s.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("------------------------------");
        int[] ints = new int[3];
        System.out.println(ints.getClass().getClassLoader());//null，此处空说明数组中的元素没有类加载器
    }
}
