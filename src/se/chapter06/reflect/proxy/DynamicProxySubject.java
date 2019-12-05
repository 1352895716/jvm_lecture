package se.chapter06.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: DynamicProxySubject
 * @Description: TODO
 * @Autor:13528
 * @Date: 2019/12/4 15:57
 * @Version 1.0
 **/
public class DynamicProxySubject implements InvocationHandler {

    private Object sub;
    public DynamicProxySubject(Subject sub){
        this.sub = sub;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(sub,args);
        return null;
    }
}
