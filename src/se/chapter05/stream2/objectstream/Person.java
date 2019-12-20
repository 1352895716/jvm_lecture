package se.chapter05.stream2.objectstream;

import java.io.Serializable;

/**
 * @ClassName: Person
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/3 9:01
 * @Version 1.0
 **/
/*
 * @Autor: 13528
 * Serializable
 * 如果使用默认的版本序列号，
 * 在类修改时（或者编译器的版本不同时）会导致序列化版本号改变，从而使反序列化失败
 * 所以强烈建议序列化版本号显示声明（如果可能的话尽量private修饰，因为
 * 版本号对继承成员没有任何用处）
 * transient 短暂的
 * static 静态的（公共的）
 * 这两种状态的字段都不能被序列化
 **/
public class Person implements Serializable{
    private transient String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
