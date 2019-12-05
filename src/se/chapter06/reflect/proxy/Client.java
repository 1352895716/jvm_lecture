package se.chapter06.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/4 16:31
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        Subject realSub = new RealSubject();
        InvocationHandler handler = new DynamicProxySubject(realSub);

        Subject obj = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),realSub.getClass().getInterfaces(),handler);
        obj.requesr();//此处能调用，是因为代理对象实现了真实对象的接口列表
        obj.requser2();

    }
}
