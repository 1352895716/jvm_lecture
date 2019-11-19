package com.zy;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName: Test08 自定义类加载器
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/12 21:01
 * @Version 1.0
 **/
public class Test08 extends ClassLoader{
    private String classLoaderName;
    private final String fileExtension = ".class";

    public Test08(String name){
        super(); //默认指定系统类加载器为父亲
        classLoaderName = name;
    }
    public Test08(ClassLoader parent,String name){
        super(parent);//指定父加载器为parent
        classLoaderName = name;
    }
    public String toString(){
        return "["+this.classLoaderName+"]";
    }
    /*
     * @Autor: 13528
     * @Description: 加载.class文件转成byte数组
     * @Date: 11:01 2019/11/14
     * @Param: [name]
     * @return: byte[]
     **/
    public byte[] loadClassData(String name){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = this.classLoaderName.replace(".","\\");
            is = new FileInputStream(name + this.fileExtension);
            int ch = 0;
            while (-1 != (ch = is.read())){
                baos.write(ch);
            }
            data = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("loaddata 被使用了");
        return data;
    }
    /*
     * @Autor: 13528
     * @Description: 返回该类的一个类对象
     * @Date: 11:03 2019/11/14
     * @Param:
     * @return:
     **/
    protected Class<?>findClass(String className)throws ClassNotFoundException{
        byte[] data = this.loadClassData(className);
        System.out.println("findClass 被使用了");
        return this.defineClass(className,data,0,data.length);
    }
    public static void test(ClassLoader classLoader) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?>clazz = classLoader.loadClass("com.zy.Test01");
        Object object = clazz.newInstance();
        System.out.println(object);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Test08 loader1 = new Test08("loader1");
        test(loader1);
    }
}
