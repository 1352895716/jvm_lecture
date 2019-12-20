package se.chapter04.annotation;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: AnnotationDemo01
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/11/24 16:53
 * @Version 1.0
 **/
@MyAnnotation(className = "se.chapter04.annotation.User",methodName = "show")
public class AnnotationDemo01 {

    @Test
    public void test01() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1.解析注解
        //1.1获取该类的字节码文件对象
        Class clz = AnnotationDemo01.class;
        //2.获取上面的注解对象
        //相当于在内存中生成了一个该注解接口的子类实现对象
        MyAnnotation myAnnotation = (MyAnnotation) clz.getAnnotation(MyAnnotation.class);
        //3.调用注解对象中定义的抽象方法，获取返回值
        String className = myAnnotation.className();//获取类名
        String methodName = myAnnotation.methodName();//获取方法名
        System.out.println(className);
        System.out.println(methodName);

        //3.1.加载该类进内存
        Class clz2 = Class.forName(className);
        //3.2.创建对象
        Object obj = clz2.newInstance();
        //3.3.获取方法对象
        Method method = clz2.getMethod(methodName);
        //3.4 执行方法
        method.invoke(obj);
    }
}
