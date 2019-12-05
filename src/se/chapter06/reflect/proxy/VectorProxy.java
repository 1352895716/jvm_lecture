package se.chapter06.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: VectorProxy
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/4 21:50
 * @Version 1.0
 **/
public class VectorProxy implements InvocationHandler{

    private Object obj;

    public VectorProxy(Object obj){
        this.obj = obj;
    }

    public static Object factory(Object obj){
        VectorProxy vp = new VectorProxy(obj);
        return Proxy.newProxyInstance(vp.getClass().getClassLoader(),obj.getClass().getInterfaces(),vp);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        /*for (Object o:args){
            System.out.println(o);
        }*/
        Object o = method.invoke(obj,args);
        System.out.println(method);
        return o;
    }

    public static void main(String[] args) {
        List v = (List) VectorProxy.factory(new Vector<String>());
        v.add("aaaa");
        v.add("bbbb");
        System.out.println(v);
    }
}
